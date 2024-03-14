package com.interview.basic.thread.print;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zqz
 * @date 2024-03-07 15:56
 */
public class FormTo100_ReentranLock {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        AtomicInteger atomicInteger = new AtomicInteger(1);

        Thread a = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (atomicInteger.get() < 100) {
                        if (atomicInteger.get() % 2 == 1) {
                            conditionA.await();// await 会释放锁， 唤醒
                        }
                        System.out.println("a=>" + atomicInteger.getAndIncrement());
                        conditionB.signalAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }};


            Thread b = new Thread() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        while (atomicInteger.get() <= 100) {
                            if (atomicInteger.get() % 2 == 0) {
                                conditionB.await();
                            }
                            System.out.println("b=>" + atomicInteger.getAndIncrement());
                            conditionA.signalAll();
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
        }
}
