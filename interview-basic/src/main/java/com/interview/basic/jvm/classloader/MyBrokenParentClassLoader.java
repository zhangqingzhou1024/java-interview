package com.interview.basic.jvm.classloader;

import java.lang.reflect.Method;

/**
 * 遵循双亲委派机制
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-14 11:51
 */
public class MyBrokenParentClassLoader extends ClassLoader {

    private String classPath;

    public MyBrokenParentClassLoader(String classPath) {
        this.classPath = classPath;
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                if(name.startsWith("com.interview.")){
                    c = findClass(name);
                }else {
                    c = getParent().loadClass(name);
                }

                long t0 = System.nanoTime();

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }


    /**
     * 重写父类方法，返回一个Class对象
     * ClassLoader中对于这个方法的注释是:
     * This method should be overridden by class loader implementations
     */
    /**
     * 重写父类方法，返回一个Class对象
     * ClassLoader中对于这个方法的注释是:
     * This method should be overridden by class loader implementations
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("MyBrokenParentClassLoader findClass");
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
        MyBrokenParentClassLoader myBrokenParentClassLoader = new MyBrokenParentClassLoader("E:/study/jvm");
        Class clazz = myBrokenParentClassLoader.loadClass("com.interview.basic.jvm.classloader.MyHello");
        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(null, null);

        System.out.println("-------");
    }
}
