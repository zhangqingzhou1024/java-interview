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
 * private void doReleaseShared() {
 *         *
 *          * Ensure that a release propagates, even if there are other
 *          * in-progress acquires/releases.  This proceeds in the usual
 *          * way of trying to unparkSuccessor of head if it needs
 *          * signal. But if it does not, status is set to PROPAGATE to
 *          * ensure that upon release, propagation continues.
 *          * Additionally, we must loop in case a new node is added
 *          * while we are doing this. Also, unlike other uses of
 *          * unparkSuccessor, we need to know if CAS to reset status
 *          * fails, if so rechecking.
 *          *
 *for(;;){
         *Node h=head;
         *if(h!=null&&h!=tail){
         *int ws=h.waitStatus;
         *if(ws==Node.SIGNAL){
         *if(!compareAndSetWaitStatus(h,Node.SIGNAL,0))
         *continue;            // loop to recheck cases
         *unparkSuccessor(h);
         *}
         *else if(ws==0&&
         *!compareAndSetWaitStatus(h,0,Node.PROPAGATE))
         *continue;                // loop on failed CAS
         *}
         *if(h==head)                   // loop if head changed
         *break;
         *}
         *}
 *
 * @author zqz
 * @date 2024-02-18 21:09
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        Semaphore windows = new Semaphore(2);

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
                        Thread.sleep(10000L);
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
