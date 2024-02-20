package com.interview.basic.jvm.classloader;

import java.lang.reflect.Method;

/**
 * 遵循双亲委派机制
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-14 11:51
 */
public class MyClassLoader extends ClassLoader {

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 重写父类方法，返回一个Class对象
     * ClassLoader中对于这个方法的注释是:
     * This method should be overridden by class loader implementations
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("MyClassLoader findClass");
        Class clazz = null;
        String classFilename = this.classPath + "/" + name.replaceAll("\\.", "/") + ".class";
        try {//E:\study\jvm\com\java
            byte[] b = FileUtils.loadFile(classFilename);
            clazz = defineClass(name, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }


    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("E:/study/jvm");
        // 在target下，所以AppCLassLoader能够识别所以，不走自定义的类加载器
        Class clazz = myClassLoader.loadClass("com.interview.basic.jvm.classloader.Hello");
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader);
        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(null, null);

        System.out.println("-------");
        System.out.println();
        /**
         * 路径需要与 class文件内部对应，会进行类文件验证
         */
        //clazz = myClassLoader.loadClass("com.interview.basic.jvm.classloader.MyHello");
        clazz = myClassLoader.loadClass("com.interview.basic.jvm.classloader.MyHello");
        classLoader = clazz.getClassLoader();

        System.out.println(classLoader);
    }

}
