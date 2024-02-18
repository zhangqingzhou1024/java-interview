package com.interview.basic.datastructure.concurrent.thread;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-07 19:36
 */
public class ThreadLocalStart {

    public static void main(String[] args) {
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        ThreadLocal<Object> objectThreadLocal02 = new ThreadLocal<>();

        objectThreadLocal.set("aaa");
        objectThreadLocal02.set("bbb");

        Object o = objectThreadLocal.get();
        System.out.println(o);

        Object o1 = objectThreadLocal02.get();
        System.out.println(o1);
    }
}
