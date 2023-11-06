package org.heimi.exceptions;

/**
 * @author heimi
 * @version 1.0
 * @description 文件操作异常
 * @date 2023-11-06 16:44
 */
public class FileOperatorException extends RuntimeException{

    public FileOperatorException(String message) {
        super(message);
    }
}
