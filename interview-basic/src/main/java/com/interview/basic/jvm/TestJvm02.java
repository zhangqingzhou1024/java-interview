package com.interview.basic.jvm;

import java.util.ArrayList;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-13 22:51
 */
public class TestJvm02 {

    private static byte[] byteArr = new byte[1024 * 1000];

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5 * 1000L);
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            System.out.println("i  = " + i);
            Thread.sleep(100L);
            arrayList.add(byteArr);
        }
    }
}
