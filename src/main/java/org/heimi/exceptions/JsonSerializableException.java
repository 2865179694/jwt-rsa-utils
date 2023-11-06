package org.heimi.exceptions;

/**
 * @author heimi
 * @version 1.0
 * @description JSON序列化异常
 * @date 2023-11-06 17:41
 */
public class JsonSerializableException extends RuntimeException{

    public JsonSerializableException(String message) {
        super(message);
    }
}
