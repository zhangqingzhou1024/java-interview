package com.interview.basic.jvm.classloader;

import java.io.FileInputStream;

/**
 * @author zqz
 * @date 2024-02-20 13:54
 */
public class FileUtils {

    public static byte[] loadFile(String classFilename) throws Exception {
        byte[] b = null;
        FileInputStream inputStream = new FileInputStream(classFilename);
        int len = inputStream.available();
        byte[] bytes = new byte[len];
        inputStream.read(bytes);
        inputStream.close();
        return bytes;
    }
}
