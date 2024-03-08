package com.interview.basic.thread.print;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zqz
 * @date 2024-03-07 15:01
 */
public class From1To100_sync {
    static AtomicInteger i = new AtomicInteger(1);
    static Object lock = new Object();
    static volatile int state = 1;

    public static void main(String[] args) {

        Thread threadA = new Thread() {
            @Override
            public void run() {
                while(i.get() < 100){
                    synchronized (lock) {
                        while (state != 1){
                            try {
                                lock.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + "==>" + i.getAndIncrement());
                        lock.notify();
                        state = 2;
                    }
                }

            }
        };


        Thread threadB = new Thread() {
            @Override
            public void run() {
                while(i.get() <= 100){
                    synchronized (lock) {
                        while (state != 2){
                            try {
                                lock.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + "==>" + i.getAndIncrement());
                        lock.notify();
                        state =1;
                    }
                }

            }
        };



        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
    }
}
