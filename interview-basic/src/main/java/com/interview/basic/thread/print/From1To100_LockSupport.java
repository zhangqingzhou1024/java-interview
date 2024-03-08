package com.interview.basic.thread.print;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zqz
 * @date 2024-03-07 15:01
 */
public class From1To100_LockSupport {
    static AtomicInteger i = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread[] threads = new Thread[2];
        threads[0] = new Thread() {
            @Override
            public void run() {
                while (i.get() < 100) {
                    System.out.println(Thread.currentThread().getName() + "==>" + i.getAndIncrement());
                    LockSupport.unpark(threads[1]);
                    LockSupport.park();

                }
            }
        };


        threads[1] = new Thread() {
            @Override
            public void run() {
                while (i.get() <= 100) {
                    LockSupport.park();
                    System.out.println(Thread.currentThread().getName() + "==>" + i.getAndIncrement());
                    LockSupport.unpark(threads[0]);
                }
            }
        };


        threads[0].setName("A");
        threads[1].setName("B");
        threads[0].start();
        threads[1].start();
    }
}
