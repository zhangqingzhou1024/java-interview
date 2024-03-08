package com.interview.basic.thread.print;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zqz
 * @date 2024-03-07 16:19
 */
public class PrintABCThreads {
    static int loop = 10;
    static int state = 0;

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        ReentrantLock lock = new ReentrantLock();
        Condition A = lock.newCondition();
        Condition B = lock.newCondition();
        Condition C = lock.newCondition();

        Thread a = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (atomicInteger.get() < loop) {
                        if (state % 3 != 0) {
                            try {
                                A.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("a-> A");
                        state++;
                        B.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread b = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (atomicInteger.get() < loop) {
                        if (state % 3 != 1) {
                            try {
                                B.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("b-> B");
                        state++;
                        C.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread c = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {

                    while (atomicInteger.get() < loop) {
                        if (state % 3 != 2) {
                            try {
                                C.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("c-> C");
                        atomicInteger.getAndIncrement();
                        state++;
                        A.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        a.start();
        b.start();
        c.start();
    }
}
