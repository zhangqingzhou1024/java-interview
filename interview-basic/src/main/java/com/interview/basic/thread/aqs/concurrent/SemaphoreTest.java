package com.interview.basic.thread.aqs.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

/**
 * 场景为： 对某种资源进行并发限流，限制最大并发数
 *
 * Semaphore 底层为AQS , 共享锁，state起始值为最大许可为 permits
 * protected final void setState(int newState) {
 *         state = newState;
 *     }
 * final int nonfairTryAcquireShared(int acquires) {
 *             for (;;) {
 *                 int available = getState();
 *                 int remaining = available - acquires;
 *                 if (remaining < 0 ||
 *                     compareAndSetState(available, remaining))
 *                     return remaining;
 *             }
 *         }
 *
 *         protected final boolean tryReleaseShared(int releases) {
 *             for (;;) {
 *                 int current = getState();
 *                 int next = current + releases;
 *                 if (next < current) // overflow
 *                     throw new Error("Maximum permit count exceeded");
 *                 if (compareAndSetState(current, next))
 *                     return true;
 *             }
 *         }
 *
 *
 * @author zqz
 * @date 2024-02-18 21:09
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        Semaphore windows = new Semaphore(5);

        int threadNum = 10;

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    try {
                        windows.acquire();
                        System.out.println(Thread.currentThread().getName() + ": 开始买票");
                        // 模拟业务场景
                        Thread.sleep(3000L);
                        System.out.println(Thread.currentThread().getName() + ": 买票成功！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        windows.release();
                    }

                }
            }).start();
        }
    }
}
