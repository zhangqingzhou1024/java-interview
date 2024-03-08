package com.interview.basic.thread.print;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zqz
 * @date 2024-03-07 15:01
 */
public class From1To100_Sempaphore {
    static AtomicInteger i = new AtomicInteger(1);
    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            try {
                while (i.get() < 100) {
                    s1.acquire();
                    System.out.println("线程-a:" + i.getAndIncrement());
                    s2.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread b = new Thread(() -> {
            try {
                while (i.get() < 100) {
                    s2.acquire();
                    System.out.println("线程-b:" + i.getAndIncrement());
                    s1.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        b.start();
    }

}
