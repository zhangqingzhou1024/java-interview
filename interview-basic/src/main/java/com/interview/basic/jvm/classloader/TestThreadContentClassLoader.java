package com.interview.basic.jvm.classloader;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-16 21:38
 */
public class TestThreadContentClassLoader {
    public static void main(String[] args) {

        Thread.currentThread().setContextClassLoader(new MyClassLoader());

        Hello hello = new Hello();

        ClassLoader classLoader = hello.getClass().getClassLoader();

        System.out.println(classLoader);

        System.out.println(classLoader.getParent());
    }
}
