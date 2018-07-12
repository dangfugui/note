package dang.fileDB.db;

import java.io.IOException;
import java.io.Serializable;

/**
 * Description: 简单key / Value 数据库接口
 *
 * @Date Create in 2017/12/13
 */
public interface DB<K extends Serializable, V extends Serializable> {

    /**
     * 保存或更改键值对
     *
     * @param key   键
     * @param value 值
     */
    void put(K key, V value);

    /**
     * 获取键 对应的值
     *
     * @param key 键
     *
     * @return 值
     */
    V get(K key);

    /**
     * 根据键删除
     *
     * @param key 键
     *
     * @return 删除的值
     */
    V remove(K key);

    /**
     * 将数据信息保存到文件
     */
    void flush() throws IOException;

    /**
     * 关闭数据库
     */
    public void close() throws IOException;

}
