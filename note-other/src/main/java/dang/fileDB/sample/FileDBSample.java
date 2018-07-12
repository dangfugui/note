package dang.fileDB.sample;

import java.io.IOException;

import dang.fileDB.db.DB;
import dang.fileDB.db.FileDB;

/**
 * Description: 文件存储 示例
 *
 * @Date Create in 2017/12/13
 */
public class FileDBSample {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        DB<Integer, User> db = new FileDB("/", "FileDBSample-User");
        System.out.println("读取索引用时：" + (System.currentTimeMillis() - start));
        // 测试插入数据速度
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setId(i);
            user.setName("name:" + i);
            user.setEmail(null);
            db.put(i, user);
        }
        System.out.println("存100万数据用时：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        db.flush();
        db.close();
        System.out.println("写入磁盘100万数据用时：" + (System.currentTimeMillis() - start));
    }
}
