package com.dang.note.security.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Description:
 * AES的全称是Advanced Encryption Standard，即高级加密标准，该算法由比利时密码学家Joan Daemen和Vincent Rijmen所设计，
 * 结合两位作者的名字，又称Rijndael加密算法，是美国联邦政府采用的一种对称加密标准，这个标准用来替代原先的DES算法，
 * 已经广为全世界所使用，已然成为对称加密算法中最流行的算法之一。
 * AES算法作为新一代的数据加密标准汇聚了强安全性、高性能、高效率、易用和灵活等优点，设计有三个密钥长度:128,192,256位，
 * 比DES算法的加密强度更高，更为安全。
 */
public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";  // 默认的加密算法
    private static final String IV = "abcdefghijklmnop";

    public static String encryptEBC(String data, String key) {
        return new String(encryptEBC(data.getBytes(), key.getBytes()));
    }

    public static byte[] encryptEBC(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(data);// 加密
            return Base64.getEncoder().encode(result);//通过Base64转码返回
        } catch (Exception e) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static String decryptEBC(String data, String key) {
        return new String(decryptEBC(data.getBytes(), key.getBytes()));
    }

    public static byte[] decryptEBC(byte[] data, byte[] key) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            //执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(data));
            return result;
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKey getSecretKey(final byte[] password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return secretKey;
            //return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte[] encryptCBC(byte[] content, byte[] keyBytes, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(keyBytes), new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(content);
            return Base64.getEncoder().encode(result);
        } catch (Exception e) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static byte[] decryptCBC(byte[] content, byte[] keyBytes, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(keyBytes), new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("exception:" + e.toString());
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String password = "1";
        String data = encryptEBC("1", password);
        System.out.println(data);
        System.out.println(decryptEBC(data, password));
        System.out.println("EBC   CBC");

        data = new String(encryptCBC("1".getBytes(), password.getBytes(), IV.getBytes()));
        System.out.println(data);
        System.out.println(new String(decryptCBC(data.getBytes(), password.getBytes(), IV.getBytes())));
    }
}
