package com.interview.basic.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-14 11:51
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 重写父类方法，返回一个Class对象
     * ClassLoader中对于这个方法的注释是:
     * This method should be overridden by class loader implementations
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String classFilename = name + ".class";
        File classFile = new File(classFilename);
        if (classFile.exists()) {
            try (FileChannel fileChannel = new FileInputStream(classFile).getChannel();) {

                FileInputStream inputStream = new FileInputStream(classFile);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int havaNext ;
                while((havaNext = inputStream.read())!= -1){
                    byteArrayOutputStream.write(havaNext);
                }
                byte[] b = byteArrayOutputStream.toByteArray();
                System.out.println(b.length);
                clazz = defineClass(classFilename, b, 0, b.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

}
