package com.interview.basic.jvm.classloader;

import java.lang.reflect.Method;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-16 16:05
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Method sayHello;
       // Class<Hello> helloClass;
       /* Class<Hello> helloClass = Hello.class;

        Method sayHello = helloClass.getMethod("sayHello");
        System.out.println("-----------------");
        sayHello.invoke(null,null);
        System.out.println("--------Hello.class end---------");

        Class<?> aClass = Class.forName("com.interview.basic.jvm.classloader.Hello");
        sayHello = helloClass.getMethod("sayHello");
        System.out.println("-----------------");
        sayHello.invoke(null,null);
        System.out.println("-------Class.forName end----------");
*/
        Hello hello = new Hello();
        Class<? extends Hello> aClass1 = hello.getClass();
        sayHello = aClass1.getMethod("sayHello");
        System.out.println("-----------------");
        sayHello.invoke(null,null);
        System.out.println("-----------------");




    }
}
