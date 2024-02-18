package com.interview.basic.thread.aqs.concurrent;

import java.util.concurrent.*;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-07 23:40
 */
public class Test {
    public static final int loadResourceThreadNum = 3;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(loadResourceThreadNum);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        ResourceLoadMainThread resourceLoadMainThread = new ResourceLoadMainThread(countDownLatch,cyclicBarrier);
        resourceLoadMainThread.start();


        OtherTestThread otherTestThread = new OtherTestThread(cyclicBarrier);
        otherTestThread.start();
    }

    static class OtherTestThread extends Thread{
        private CyclicBarrier cyclicBarrier;

        public OtherTestThread(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 15);
                System.out.println("其他资源加载完毕。。。");

                cyclicBarrier.await();

                System.out.println("OtherTestThread - 其他资源和当前资源都有加载完毕！");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    static class ResourceLoadMainThread extends Thread {
        private CountDownLatch countDownLatch;
        private CyclicBarrier cyclicBarrier;

        public ResourceLoadMainThread(CountDownLatch countDownLatch,CyclicBarrier cyclicBarrier) {
            this.countDownLatch = countDownLatch;
            this.cyclicBarrier = cyclicBarrier;
        }


        @Override
        public void run() {
            long countDownLatchCount = this.countDownLatch.getCount();
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(10));
            for (int i = 0; i < countDownLatchCount; i++) {
                ResourceLoadSonThread resourceLoadSonThread = new ResourceLoadSonThread(countDownLatch);
                threadPoolExecutor.execute(resourceLoadSonThread);
            }

            try {
                countDownLatch.await();
                System.out.println("所有的资源已加载完成，准备进行测试。。。");

                cyclicBarrier.await();
                System.out.println("ResourceLoadMainThread - 其他资源和当前资源都有加载完毕！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ResourceLoadSonThread implements Runnable {
        CountDownLatch countDownLatch = null;


        public ResourceLoadSonThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 5);
                System.out.println(Thread.currentThread().getName() + ", 加载资源成功！");
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
