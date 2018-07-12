package dang.fileDB.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: 摘要算法 工具类
 *
 * @Date Create in 2017/12/13
 */
public class DigestUtils {

    /**
     * sha算法
     *
     * @param bytes 计算摘要的内容
     *
     * @return 摘要
     */
    public static byte[] sha1(byte[] bytes) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        messageDigest.update(bytes);
        byte[] shaBytes = messageDigest.digest();
        return shaBytes;
    }

    /**
     * 生成md5
     *
     * @param bytes 需要计算md5的内容
     *
     * @return md5
     */
    public static String md5String(byte[] bytes) {
        byte[] md5Bytes = md5Bytes(bytes);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int value = ((int) md5Bytes[i]) & 0xff;
            if (value < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(value));
        }
        return hexValue.toString();
    }

    /**
     * 生成md5 的bytes 表示
     *
     * @param bytes 需要计算md5的内容
     *
     * @return md5的bytes表示
     */
    public static byte[] md5Bytes(byte[] bytes) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] md5Bytes = md5.digest(bytes);
        return md5Bytes;
    }

}
