package com.interview.basic.jvm.classloader;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-16 14:54
 */
public class Hello {

    static {
        System.out.println("static med --> hello world!");
    }

    public static void sayHello() {
        System.out.println("Hello,I am ....");
    }
}
