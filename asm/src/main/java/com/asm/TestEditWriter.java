package com.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

/**
 * @author zqz
 * @date 2024-01-29 16:26
 */
public class TestEditWriter {
    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("com.asm.Hello");

        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        // 中间层，适配器， 固定结构，拆分，修改，组装
        ClassVisitor editVisitor = new ClassVisitor(ASM5, classWriter) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, name + "$1", signature, superName, interfaces);
            }
        };
        classReader.accept(editVisitor, 0);

        byte[] bytes = classWriter.toByteArray();

        String fPath = "D:\\study_projects\\java-interview\\asm\\target\\classes\\com\\asm\\HelloCopyEdit.class";

        Files.write(new File(fPath).toPath(), bytes);


    }
}
