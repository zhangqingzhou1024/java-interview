package com.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author zqz
 * @date 2024-01-29 16:26
 */
public class TestWriter {
    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("com.asm.Hello");

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classReader.accept(classWriter, 0);

        byte[] bytes = classWriter.toByteArray();

        String fPath = "D:\\study_projects\\java-interview\\asm\\target\\classes\\com\\asm\\HelloCopy.class";

        Files.write(new File(fPath).toPath(),bytes);


    }
}
