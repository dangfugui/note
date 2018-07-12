package dang.fileDB.sample;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dang.fileDB.db.ConcurrentFileDB;
import dang.fileDB.db.DB;

/**
 * Description: 文件存储 示例
 *
 * @Date Create in 2017/12/13
 */
public class ConcurrentFileDBSample {
    private DB<Integer, User> db;

    public static void main(String[] args) throws IOException, InterruptedException {
        ConcurrentFileDBSample sample = new ConcurrentFileDBSample();
        DB db = sample.getDB();
        sample.putData();
        sample.flushFile();
        sample.getData();
        db.close();
    }

    /**
     * 向数据库添加数据
     */
    public static void addUser(DB db, int start, int end) {
        for (int i = start; i < end; i++) {
            User user = new User();
            user.setId(i);
            user.setName("name:" + i);
            user.setEmail(null);
            db.put(i, user);
        }
    }

    /**
     * 测试 写磁盘用时
     */
    private void flushFile() throws IOException {
        long start = System.currentTimeMillis();
        db.flush();
        System.out.println("写入磁盘100万数据用时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 测试 多线程存数据用时
     */
    private void putData() throws InterruptedException {
        // 测试插入数据速度
        final CountDownLatch endGate = new CountDownLatch(10);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    addUser(db, finalI * 100000, (finalI + 1) * 100000);
                    endGate.countDown();
                }
            });
        }
        cachedThreadPool.shutdown();
        endGate.await();
        System.out.println("存100万数据用时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 测试  获取 数据库用时
     */
    public DB<Integer, User> getDB() throws IOException {
        long start = System.currentTimeMillis();
        ;
        db = new ConcurrentFileDB("/", "ConcurrentFileDBSample-User");
        System.out.println("读取索引用时：" + (System.currentTimeMillis() - start));
        return db;
    }

    /**
     * 测试 读取文件数据用时
     */
    public void getData() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i = i + 5) {
            User user = new User();
            user.setId(i);
            user.setName("name:" + i);
            user.setEmail(null);
            User dbUser = db.get(i);
            if (!user.equals(dbUser)) {
                System.out.println("数据不一致！！");
            }
        }
        System.out.println("读取文件里的20万数据用时：" + (System.currentTimeMillis() - start));
    }
}
