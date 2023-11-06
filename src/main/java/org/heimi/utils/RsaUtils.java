package org.heimi.utils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author heimi
 * @version 1.0
 * @description 主要负责RSA加密相关的操作
 * @date 2023-11-06 17:16
 */
public class RsaUtils {

    /**
    * @description 获取文件中的公钥
    * @param publicKeyFilename 公钥地址
    * @return 公钥
    * @author heimi
    * @date 2023-11-06 17:26
    */
    public static PublicKey getPublicKeyFromFile(String publicKeyFilename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // 从文件中读取公钥
        byte[] bytes = FileOperatorUtils.readFile(publicKeyFilename);
        // 使用Base64解码器解码
        bytes = Base64.getDecoder().decode(bytes);
        // 将字节数组解码得到公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bytes);
        // 获取RSA密钥实例
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 生成公钥
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    /**
    * @description 获取文件中的私钥
    * @param privateKeyFilename 私钥地址
    * @return 私钥
    * @author heimi
    * @date 2023-11-06 17:33
    */
    public static PrivateKey getPrivateKeyFromFile(String privateKeyFilename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // 从文件中读取出私钥
        byte[] bytes = FileOperatorUtils.readFile(privateKeyFilename);
        // 使用base64解码器对私钥进行解码
        bytes = Base64.getDecoder().decode(bytes);
        // 从字节数组中解码得到私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
        // 获取RSA密钥实例
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 生成私钥
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }
}
