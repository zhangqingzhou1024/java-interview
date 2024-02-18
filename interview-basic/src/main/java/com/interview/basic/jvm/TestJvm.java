package com.interview.basic.jvm;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-13 22:35
 */
public class TestJvm {

    public static void main(String[] args) {
        testProcess();
    }

    public static void testProcess() {
        int i = 3;
        int j = 5;

        int k = i * j;

        System.out.println("result:" + k);
    }
}
