package dang.fileDB.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Description: 数据的 工具类
 *
 * @Date Create in 2017/12/13
 */
public class DataUtils {

    /**
     * 生成对象序列化出的byte数组
     *
     * @param data
     *
     * @return
     *
     * @throws IOException
     */
    public static byte[] serializableToBytes(Serializable data) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(data);
        oo.close();
        bo.close();
        return bo.toByteArray();
    }

    /**
     * 把序列化出的byte数组 反序列化成对象
     *
     * @param bytes 序列化的byte数组
     *
     * @return 反序列化出的对象
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object bytesToserializable(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream input = new ObjectInputStream(inputStream);
        return input.readObject();
    }

}
