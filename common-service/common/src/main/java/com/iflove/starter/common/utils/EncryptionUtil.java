package com.iflove.starter.common.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 字符串加密工具类
 */

public class EncryptionUtil {
    public static final String PASSWORD = "1145141919810";

    /**
     * 生成AES密钥
     *
     * @param password 密码
     * @return 密钥
     * @throws NoSuchAlgorithmException 密钥生成算法不支持异常
     */
    private static SecretKey generateAESKey(String password) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = digest.digest(passwordBytes);
        return new SecretKeySpec(keyBytes, "AES");
    }

    /**
     * 对称加密算法AES加密
     *
     * @param plaintext 明文
     * @param password  密码
     * @return 加密后的密文
     * @throws Exception 加密异常
     */
    public static String encryptWithAES(String plaintext, String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = generateAESKey(password);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 对称加密算法AES解密
     *
     * @param ciphertext 密文
     * @param password   密码
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static String decryptWithAES(String ciphertext, String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = generateAESKey(password);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 对称加密算法AES判断是否匹配
     *
     * @param plaintext 明文
     * @param ciphertext 秘文
     * @param password 密码
     * @return 是否匹配
     */
    public static boolean matchesAES(String plaintext, String ciphertext, String password) throws Exception {
        String decrypt = decryptWithAES(ciphertext, password);
        return Objects.equals(decrypt, plaintext);
    }

    /**
     * 生成RSA密钥对
     *
     * @return 密钥对
     * @throws NoSuchAlgorithmException 密钥生成算法不支持异常
     */
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取RSA公钥的Base64编码字符串
     *
     * @return RSA公钥Base64编码字符串
     */
    public static String getRSAPublicKeyString(PublicKey publicKey) {
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            return Base64.getEncoder().encodeToString(keyFactory.generatePublic(publicKeySpec).getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据Base64编码的字符串还原为RSA公钥
     *
     * @param publicKeyString RSA公钥Base64编码字符串
     * @return RSA公钥
     */
    public static PublicKey getPublicKey(String publicKeyString) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
            return keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取RSA私钥的Base64编码字符串
     *
     * @return RSA私钥Base64编码字符串
     */
    public static String getRSAPrivateKeyString(PrivateKey privateKey) {
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            return Base64.getEncoder().encodeToString(keyFactory.generatePrivate(privateKeySpec).getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据Base64编码的字符串还原为RSA私钥
     *
     * @param privateKeyString RSA私钥Base64编码字符串
     * @return RSA私钥
     */
    public static PrivateKey getPrivateKey(String privateKeyString) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
            return keyFactory.generatePrivate(privateKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 非对称加密算法RSA加密
     *
     * @param plaintext 明文
     * @param publicKey 公钥
     * @return 加密后的密文
     * @throws Exception 加密异常
     */
    public static String encryptWithRSA(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        KeyPair keyPair = generateRSAKeyPair();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 非对称加密算法RSA解密
     *
     * @param ciphertext 密文
     * @param privateKey 私钥
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static String decryptWithRSA(String ciphertext, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        KeyPair keyPair = generateRSAKeyPair();
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 哈希算法SHA-256
     *
     * @param plaintext 明文
     * @return 哈希值
     * @throws NoSuchAlgorithmException 哈希算法不支持异常
     */
    public static String hashWithSHA256(String plaintext) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(plaintext.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Base64 编码
     *
     * @param plainText 内容
     * @return 十六进制字符串
     */
    public static String encodeBase64(String plainText) {
        byte[] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(plainBytes);
    }

    /**
     * Base64 解码
     *
     * @param base64Text 十六进制字符串
     * @return 内容
     */
    public static String decodeBase64(String base64Text) {
        byte[] base64Bytes = Base64.getDecoder().decode(base64Text);
        return new String(base64Bytes, StandardCharsets.UTF_8);
    }
}
