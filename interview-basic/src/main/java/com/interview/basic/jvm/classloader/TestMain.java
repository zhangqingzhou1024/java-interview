package com.interview.basic.jvm.classloader;

import java.lang.reflect.Method;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-16 14:58
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.loadClass("com.interview.basic.jvm.classloader.Hello");
        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(null, null);

        System.out.println("-------");
    }

}
