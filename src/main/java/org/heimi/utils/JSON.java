package org.heimi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.heimi.exceptions.JsonSerializableException;

/**
 * @author heimi
 * @version 1.0
 * @description json序列化相关操作
 * @date 2023-11-06 17:35
 */
public class JSON {

    // json序列化器
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
    * @description 将json序列化成字符串
    * @param o 任意对象
    * @author heimi
    * @date 2023-11-06 17:42
    */
    public static String toString(Object o) {
        if (o != null) {
            // 判断是否是字符串类型
            if (o instanceof String) {
                return (String) o;  // 直接返回
            }
            try {
                return OBJECT_MAPPER.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                throw new JsonSerializableException("json序列化失败："+e);
            }
        } else {
            return null;  // 直接返回空内容
        }
    }

    /**
    * @description 将json字符串转化为java对象
    * @param json 需要转化的json字符串
     * @param tClass 目标类型
    * @return T
    * @author heimi
    * @date 2023-11-06 17:46
    */
    public static <T> T toJavaBean(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new JsonSerializableException("json解析出错："+e);
        }
    }
}
