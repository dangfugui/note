package dang.fileDB.db;

import java.io.IOException;
import java.io.Serializable;

/**
 * Description: 数据库的 工厂类
 *
 * @Date Create in 2017/12/13
 */
public class DBFactory {

    /**
     * 获取文件数据库
     *
     * @param path   工作路径
     * @param dbName 数据库名称
     *
     * @return DB
     *
     * @throws IOException IO异常
     */
    public static <K extends Serializable, V extends Serializable> DB getFileDB(String path, String dbName)
            throws IOException {
        return new FileDB<K, V>(path, dbName);
    }

    /**
     * 获取并发文件数据库
     *
     * @param path   工作路径
     * @param dbName 数据库名称
     *
     * @return DB
     *
     * @throws IOException IO异常
     */
    public static <K extends Serializable, V extends Serializable> DB getConcurrentFileDB(String path, String dbName)
            throws IOException {
        return new ConcurrentFileDB<K, V>(path, dbName);
    }
}
