package org.heimi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.heimi.model.Payload;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

/**
 * @author heimi
 * @version 1.0
 * @description JWT相关操作
 * @date 2023-11-06 17:47
 */
public class JWT {

    // 默认的键
    private static final String JWT_PAYLOAD_KEY = "auth";

    /**
    * @description 产生token令牌，有效时间秒
    * @param obj 载荷主体数据
     * @param privateKey 私钥
     * @param time 存活时间
     * @param key 如果传入空则使用默认的auth
    * @return token令牌
    * @author heimi
    * @date 2023-11-06 17:54
    */
    public static String generateTokenSeconds(Object obj, PrivateKey privateKey, int time, String key) {
        // 如果传入空字符穿使用默认的键
        key = StringUtils.isNotBlank(key) ? key : JWT_PAYLOAD_KEY;
        return Jwts.builder()
                .claim(key, JSON.toString(obj))
                .setId(new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes())))
                .setExpiration(DateTime.now().plusSeconds(time).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
    * @description 解析令牌中的信息
    * @param token 令牌
     * @param publicKey 公钥
     * @param type 模型的类型
     * @param key 信息在jwt中存储的主体部分
    * @return body部分
    * @author heimi
    * @date 2023-11-06 18:59
    */
    public static <T> Payload<T> getPayload(String token, PublicKey publicKey, Class<T> type, String key) {
        key = StringUtils.isNotBlank(key) ? key : JWT_PAYLOAD_KEY;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        Claims body = claimsJws.getBody();  // 获取载荷部分
        Payload<T> payload = new Payload<>();  // 用于存储数据
        payload.setId(body.getId());  // 获取id
        payload.setData(JSON.toJavaBean(body.get(key).toString(), type));  // 封装数据
        payload.setTime(body.getExpiration());  // 封装时间
        return payload;
    }
}
