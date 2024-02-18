package com.interview.basic.thread.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * Java利用JOL工具分析对象分布:
 * https://blog.csdn.net/changyinling520/article/details/130937244
 *
 * @author zqz
 * @date 2024-02-17 11:31
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        Object obj = new Object();
        // Java利用JOL工具分析对象分布
        String printable = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(printable);
        /**
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
        //  加锁
        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "-->" + ClassLayout.parseInstance(obj).toPrintable());
            }
        }, "thread").start();
    }
}
