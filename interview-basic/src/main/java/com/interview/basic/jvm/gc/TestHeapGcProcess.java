package com.interview.basic.jvm.gc;

import java.util.ArrayList;

/**
 * 打开 jvisualvm 观察内存gc 过程
 * https://blog.csdn.net/han5606050/article/details/127666940
 *  -Xmx300m -Xms300m
 * @author zqz
 * @version 1.0
 * @date 2020-01-13 22:51
 */
public class TestHeapGcProcess {

    /**
     * 1T = 1024G
     * 1G=1024M
     * 1M=1024KB
     * 1Kb=1024 byte
     * 1byte=8 bit
     * <p>
     * 1bit 就是 0 or 1
     */
    // 即每次添加 100 K
    //private static byte[] byteArr = new byte[100 * 1024];
    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5 * 1000L);
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 1; i < 100000; i++) {
            System.out.println("i  = " + i);
            Thread.sleep(30L);
            arrayList.add(new byte[100 * 1024]);
        }

        System.out.println(arrayList.size());
    }

    /**
     * 限制执行时内存情况：
     * add VM options:
     * -Xmx300m -Xms300m
     *
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at com.interview.basic.jvm.gc.TestHeapGcProcess.main(TestHeapGcProcess.java:33)
     * Disconnected from the target VM, address: '127.0.0.1:56804', transport: 'socket'
     *
     */
}
