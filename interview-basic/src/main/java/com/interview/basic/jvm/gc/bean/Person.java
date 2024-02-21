package com.interview.basic.jvm.gc.bean;

/**
 * @author zqz
 * @date 2024-02-20 19:21
 */
public class Person {
    static int a =2;
    static long b =3;

    int c =2;
    long d =4;
    Man man1 = new Man();
    Man man2;

    public Person(int c, long d) {
        this.c = c;
        this.d = d;
    }
}
