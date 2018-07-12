package dang.fileDB.db;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dang.fileDB.utils.DataUtils;
import dang.fileDB.utils.DigestUtils;

/**
 * Description: 基于文件存储的 key / Value 数据库接口
 *
 * @Date Create in 2017/12/13
 */
public class FileDB<K extends Serializable, V extends Serializable> implements DB<K, V> {

    // 数据文件后缀
    private static final String DATA_SUFFIX = ".data";
    // 元数据文件后缀，包括索引和已删除数据
    private static final String META_SUFFIX = ".meta";
    // 文件db的索引
    private Map<String, Index> indexMap = new ConcurrentHashMap<String, Index>();
    // 要删除的db数据索引
    private Map<String, Index> deleteIndexMap = new ConcurrentHashMap<String, Index>();
    // 内存中要存入的数据
    private Map<K, V> data = new ConcurrentHashMap<K, V>();
    // 使用软引用缓存数据
    private Map<K, SoftReference<V>> cache = new ConcurrentHashMap<K, SoftReference<V>>();
    // 值数据随机读写文件
    private RandomAccessFile dbFile;
    // 值数据文件
    private File dataFile;
    // 索引文件
    private File metaFile;

    /**
     * 文件数据库构造函数
     *
     * @param path   工作路径
     * @param dbName 数据库名称
     *
     * @throws IOException IO异常
     */
    public FileDB(String path, String dbName) throws IOException {
        dataFile = new File(path + dbName + DATA_SUFFIX);
        if (!dataFile.exists()) {     // 如果数据库文件不存在  创建数据库文件
            dataFile.createNewFile();
        }
        dbFile = new RandomAccessFile(dataFile, "rw");
        metaFile = new File(path + dbName + META_SUFFIX);
        if (metaFile.exists()) {  // 如果存在索引文件  把索引加载到内容
            loadMeta();
        }
    }

    /**
     * 获取数据的摘要
     *
     * @param data 数据
     *
     * @return 摘要（MD5）
     */
    private static String getDigest(Serializable data) {
        try {
            return DigestUtils.md5String(DataUtils.serializableToBytes(data));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存或更改键值对
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void put(K key, V value) {
        String indexKey = getDigest(key);
        Index index = indexMap.get(indexKey);
        if (index != null) {  //  如果这个key已存在
            if (index.getDigest().equals(getDigest(value))) {     // 内容和之前相同
                return;
            } else {
                data.put(key, value);
                deleteIndexMap.put(indexKey, index);
            }
        } else {
            Index deleteIndex = deleteIndexMap.get(indexKey);
            if (deleteIndex == null) {   //  如果回收站没有 则创建
                data.put(key, value);
            } else {        // 把回收站的移动回索引
                indexMap.put(indexKey, index);
                deleteIndexMap.remove(indexKey);
            }
        }
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
        // 从 内存，缓存，文件中 依次尝试获取
        V value = data.get(key);
        if (value == null) {
            SoftReference<V> soft = cache.get(key);
            if (soft != null) {
                value = soft.get();
                return value;
            }
        }
        if (value == null) {
            value = getData(key);
            cache.put(key, new SoftReference<V>(value));
        }
        return value;
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
        V value = data.remove(key);
        if (value == null) {
            SoftReference<V> soft = cache.remove(key);
            if (soft != null) {
                value = soft.get();
            }
        }
        if (value == null) {
            value = removeData(key);
        }
        return value;
    }

    /**
     * 将数据信息保存到文件
     */
    @Override
    public void flush() throws IOException {
        // 如果删除的数据比真实数据多  把未删除数据移到新文件删除旧文件
        if (deleteIndexMap.size() > indexMap.size()) {
            copyingFile();
        }
        flushData();
        flushMeta();
    }

    /**
     * 关闭数据库
     */
    @Override
    public void close() throws IOException {
        dbFile.close();
    }

    /**
     * 把文件中的有用数据复制到新文件  删除老文件
     */
    private void copyingFile() throws IOException {
        File newFile = new File(dataFile.getPath() + ".swap");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        RandomAccessFile out = new RandomAccessFile(newFile, "rw");
        HashMap<String, Index> newIndexMap = new HashMap<String, Index>(indexMap.size());
        try {
            for (Map.Entry<String, Index> entry : indexMap.entrySet()) {
                Index index = entry.getValue();
                dbFile.seek(index.getPos());
                int len = dbFile.readInt();
                byte[] bytes = new byte[len];
                dbFile.read(bytes);
                Index newIndex = new Index(out.length(), index.getDigest());
                newIndexMap.put(entry.getKey(), newIndex);
                out.writeInt(len);
                out.write(bytes);
            }
        } finally {
            out.close();
        }
        dbFile.close();
        dataFile.delete();
        newFile.renameTo(dataFile);
        dataFile = new File(dataFile.getPath());
        dbFile = new RandomAccessFile(dataFile, "rw");
        indexMap = newIndexMap;
        deleteIndexMap.clear();
    }

    /**
     * 把内存中的数据传存储到文件
     *
     * @throws IOException IO异常
     */
    private void flushData() throws IOException {
        dbFile.seek(dbFile.length());
        for (Map.Entry<K, V> entry : data.entrySet()) {
            byte[] data = DataUtils.serializableToBytes(entry.getValue());
            indexMap.put(getDigest(entry.getKey()), new Index(dbFile.length(), getDigest(entry.getValue())));
            dbFile.writeInt(data.length);
            dbFile.write(data);
        }
        dbFile.getFD().sync(); // getFD()会返回文件描述符，其sync方法会确保文件内容保存到设备上
        data.clear();
    }

    /**
     * 把索引信息 存储到文件
     *
     * @throws IOException
     */
    private void flushMeta() throws IOException {
        if (!metaFile.exists()) {
            metaFile.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(metaFile);
        DataOutputStream out = new DataOutputStream(fileOutputStream);

        try {
            out.writeInt(indexMap.size());
            for (Map.Entry<String, Index> entry : indexMap.entrySet()) {
                out.writeUTF(entry.getKey());
                out.writeLong(entry.getValue().getPos());
                out.writeUTF(entry.getValue().getDigest());
            }
            out.writeInt(deleteIndexMap.size());
            for (Map.Entry<String, Index> entry : deleteIndexMap.entrySet()) {
                out.writeUTF(entry.getKey());
                out.writeLong(entry.getValue().getPos());
                out.writeUTF(entry.getValue().getDigest());
            }
        } finally {
            out.flush();
            out.close();
            fileOutputStream.close();
        }
    }

    /**
     * 把索引加载到内存(indexMap 和 deleteIndexMap)
     *
     * @throws IOException IO异常
     */
    private void loadMeta() throws IOException {
        DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(metaFile)));
        try {
            int indexSize = input.readInt();
            indexMap = new ConcurrentHashMap<String, Index>();
            for (int i = 0; i < indexSize; i++) {
                String key = input.readUTF().intern();
                indexMap.put(key, new Index(input.readLong(), input.readUTF()));
            }
            int deleteIndexSize = input.readInt();
            deleteIndexMap = new ConcurrentHashMap<String, Index>(deleteIndexSize);
            for (int i = 0; i < deleteIndexSize; i++) {
                String key = input.readUTF().intern();
                deleteIndexMap.put(key, new Index(input.readLong(), input.readUTF()));
            }
        } finally {
            input.close();
        }
    }

    /**
     * 从文件系统中获取 值
     *
     * @param key 键
     *
     * @return 值
     */
    private V getData(K key) {
        Index index = indexMap.get(getDigest(key));
        if (index == null) {    // 索引中不存在数据 返回null
            return null;
        }
        try {
            dbFile.seek(index.getPos());
            int size = dbFile.readInt();
            byte[] bytes = new byte[size];
            dbFile.read(bytes);
            return (V) DataUtils.bytesToserializable(bytes);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 移除数据 （只删除索引）
     *
     * @param key
     *
     * @return 被删除的数据
     */
    private V removeData(K key) {
        String indexKey = getDigest(key);
        Index index = indexMap.get(indexKey);
        if (index == null) { //  不存value
            return null;
        }
        V value = getData(key);
        deleteIndexMap.put(indexKey, index);
        indexMap.remove(indexKey);
        return value;
    }

    /**
     * 索引对象
     */
    class Index {
        private long pos;       // 数据的位置
        private String digest;  // 数据的摘要    md5

        public Index(long pos, String digest) {
            this.pos = pos;
            this.digest = digest;
        }

        public long getPos() {
            return pos;
        }

        public String getDigest() {
            return digest;
        }
    }
}
