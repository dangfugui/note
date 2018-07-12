package com.dang.note.security;

/**
 * RSA ：RSA非对称加密算法是1977年由Ron Rivest、 Adi Shamirh和LenAdleman开发   *  的， RSA取名来
 * 自开发他们三者的名字。
 * 参考：http://blog.csdn.net/wangqiuyun/article/details/42143957
 */

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {

    /**
     * 字节数据转字符串专用集合
     */
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    static class Key {
        private RSAPrivateKey privateKey;
        private RSAPublicKey publicKey;

        public Key() {
            createKey(1024);
        }

        public Key(int keySize) {
            createKey(keySize);
        }

        private void createKey(int keySize) {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = null;
            try {
                keyPairGen = KeyPairGenerator.getInstance("RSA", "BC");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 初始化密钥对生成器，密钥大小为96-1024位
            //            keyPairGen.initialize(keySize, new SecureRandom());
            keyPairGen.initialize(keySize);
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            publicKey = (RSAPublicKey) keyPair.getPublic();
        }

        public RSAPrivateKey getPrivateKey() {
            return privateKey;
        }

        public RSAPublicKey getPublicKey() {
            return publicKey;
        }

        public static RSAPrivateKey getPrivateKey(String privateKeyStr) {
            try {
                byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException("无此算法");
            } catch (InvalidKeySpecException e) {
                throw new IllegalArgumentException("私钥非法");
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("私钥数据为空");
            }
        }

        public static RSAPublicKey getPublicKey(String str) {
            try {
                byte[] buffer = Base64.getDecoder().decode(str);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
                return (RSAPublicKey) keyFactory.generatePublic(keySpec);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException("无此算法");
            } catch (InvalidKeySpecException e) {
                throw new IllegalArgumentException("公钥非法");
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("公钥数据为空");
            }
        }

        public String getPrivateKeyString() {
            return new String(Base64.getEncoder().encode(privateKey.getEncoded()));
        }

        public String getPublicKeyString() {
            return new String(Base64.getEncoder().encode(publicKey.getEncoded()));
        }
    }

    /**
     * 私钥加密过程
     *
     * @param privateKey
     *            私钥
     * @param plainTextData
     *            明文数据
     * @return
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt(java.security.Key privateKey, String plainTextData)
            throws Exception {
        byte[] bytes = Base64.getEncoder().encode(encrypt(privateKey, plainTextData.getBytes()));
        return new String(bytes);
    }

    public static byte[] encrypt(java.security.Key privateKey, byte[] plainTextData)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥解密过程
     *
     * @param privateKey
     *            私钥
     * @param cipherData
     *            密文数据
     * @return 明文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(java.security.Key privateKey, String cipherData)
            throws Exception {
        return new String(decrypt(privateKey, Base64.getDecoder().decode(cipherData.getBytes())));
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey
     *            公钥
     * @param cipherData
     *            密文数据
     * @return 明文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static byte[] decrypt(java.security.Key publicKey, byte[] cipherData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    public static void main(String[] args) throws Exception {
        Key key = new Key(64);
        System.out.println(key.getPrivateKeyString());
        System.out.println(key.getPublicKeyString());
        System.out.println(key.getPrivateKeyString().length());
        System.out.println(key.getPublicKeyString().length());
        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText = "12345678";
        //公钥加密过程
        String cipherData = encrypt(key.getPublicKey(), plainText);
        //私钥解密过程
        String restr = decrypt(key.getPrivateKey(), cipherData);
        System.out.println("原文：" + plainText);
        System.out.println("加密密文：" + cipherData);
        System.out.println("解密：" + restr);
        System.out.println();
    }
}