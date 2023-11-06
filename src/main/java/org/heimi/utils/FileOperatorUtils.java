package org.heimi.utils;

import org.heimi.exceptions.FileOperatorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author heimi
 * @version 1.0
 * @description 主要负责对硬盘中的文件进行操作
 * @date 2023-11-06 16:29
 */
public class FileOperatorUtils {

    /**
    * @description 将文件的内容以字节的形式读取出来
    * @param filename 读取文件的路径
    * @return byte[] 读取到的字节数组
    * @author heimi
    * @date 2023-11-06 16:38
    */
    public static byte[] readFile(String filename) throws IOException {
        Path filePath = new File(filename).toPath();
        return Files.readAllBytes(filePath);
    }

    /**
    * @description 将字节流写入到目标文件中
    * @param destFilename 写入的文件地址
     * @param bytes 需要写入文件的字节
    * @author heimi
    * @date 2023-11-06 16:46
    */
    public static void writeToFile(String destFilename, byte[] bytes) throws IOException {
        File file = new File(destFilename);
        // 判断文件是否存在，如果不存在创建文件
        if (!file.exists()) {
            boolean flag = file.createNewFile();
            // 判断文件是否创建成功
            if (!flag) {
                throw new FileOperatorException("文件创建失败！");
            }
        }
        Files.write(file.toPath(), bytes);
    }
}
