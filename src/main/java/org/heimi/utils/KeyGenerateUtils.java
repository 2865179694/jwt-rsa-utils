package org.heimi.utils;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author heimi
 * @version 1.0
 * @description 主要负责rsa密钥的生成
 * @date 2023-11-06 16:26
 */
public class KeyGenerateUtils {

    // 默认密钥的长度
    private static final int DEFAULT_KEY_SIZE = 2048;

    /**
    * @description 生成公钥文件和私钥文件
    * @param publicKeyFilename 生成公钥存储的地址
     * @param privateKeyFilename 生成私钥存储的地址
     * @param salt 密钥字符串
     * @param size 密钥长度
    * @author heimi
    * @date 2023-11-06 17:14
    */
    public static void generateKeyFile(String publicKeyFilename, String privateKeyFilename, String salt, int size) throws NoSuchAlgorithmException, IOException {
        // 获取RSA算法的实例
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // 创建一个 SecureRandom 实例，并使用salt字符串作为种子。
        SecureRandom secureRandom = new SecureRandom(salt.getBytes());
        int keySize = Math.max(size, DEFAULT_KEY_SIZE);  // 选取最大的数值作为密钥长度
        // 初始化RSA算法实例
        keyPairGenerator.initialize(keySize, secureRandom);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥编码成字节数组
        byte[] publicKeyEncoded = keyPair.getPublic().getEncoded();
        // 转化为Base64
        publicKeyEncoded = Base64.getEncoder().encode(publicKeyEncoded);
        // 将公钥写入文件
        FileOperatorUtils.writeToFile(publicKeyFilename, publicKeyEncoded);
        // 获取私钥
        byte[] privateKeyEncoded = keyPair.getPrivate().getEncoded();
        // 转化为base64
        privateKeyEncoded = Base64.getEncoder().encode(privateKeyEncoded);
        // 将私钥写入文件中
        FileOperatorUtils.writeToFile(privateKeyFilename, privateKeyEncoded);
    }
}
