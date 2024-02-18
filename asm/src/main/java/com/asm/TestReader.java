package com.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM5;


/**
 * @author zqz
 * @date 2024-01-29 15:52
 */
public class TestReader {
    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("com.asm.Hello");

        ClassVisitor classVisitor = new ClassVisitor(ASM5) {

            @Override
            public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
                System.out.println("className:" + s);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                System.out.println("visitMethod:" + name);
                return null;
            }
        };

        classReader.accept(classVisitor, 0);
    }
}
