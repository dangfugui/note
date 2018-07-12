package dang.fileDB.db;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description: 线程安全的文件数据库
 *
 * @Date Create in 2017/12/15
 */
public class ConcurrentFileDB<K extends Serializable, V extends Serializable> implements DB<K, V> {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private FileDB<K, V> db;

    /**
     * 文件数据库构造函数
     *
     * @param path   工作路径
     * @param dbName 数据库名称
     *
     * @throws IOException IO异常
     */
    public ConcurrentFileDB(String path, String dbName) throws IOException {
        db = new FileDB<K, V>(path, dbName);
    }

    /**
     * 保存或更改键值对
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void put(K key, V value) {
        lock.writeLock().lock();
        db.put(key, value);
        lock.writeLock().unlock();
    }

    /**
     * 获取键 对应的值
     *
     * @param key 键
     *
     * @return 值
     */
    @Override
    public V get(K key) {
        lock.readLock().lock();
        V res = db.get(key);
        lock.readLock().unlock();
        return res;
    }

    /**
     * 根据键删除
     *
     * @param key 键
     *
     * @return 删除的值
     */
    @Override
    public V remove(K key) {
        lock.writeLock().lock();
        V res = db.remove(key);
        lock.writeLock().unlock();
        return res;
    }

    /**
     * 将数据信息保存到文件
     */
    @Override
    public void flush() throws IOException {
        lock.writeLock().lock();
        db.flush();
        lock.writeLock().unlock();
    }

    /**
     * 关闭数据库
     */
    @Override
    public void close() throws IOException {
        lock.writeLock().lock();
        db.close();
        lock.writeLock().unlock();
    }

}
