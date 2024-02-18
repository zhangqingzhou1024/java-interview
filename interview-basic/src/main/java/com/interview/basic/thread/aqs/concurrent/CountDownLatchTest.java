package com.interview.basic.thread.aqs.concurrent;

import java.util.concurrent.*;

/**
 * CountDownLatchTest
 * 倒计时器
 * <p>
 * 当前线程等待其他线程执行，等其他线程执行完成后，本线程（一个）才执行
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-08 21:35
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        // 线程个数
        int threadNum = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        // 资源控制总线程
        ResourceLoadMainThread resourceLoadMainThread = new ResourceLoadMainThread(countDownLatch);
        resourceLoadMainThread.start();
    }

    /**
     * 资源控制总线程
     */
    static class ResourceLoadMainThread extends Thread {
        private CountDownLatch countDownLatch;

        public ResourceLoadMainThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }


        @Override
        public void run() {
            long countDownLatchCount = this.countDownLatch.getCount();
            ThreadFactory threadFactory = Executors.defaultThreadFactory();

            // 线程使用线程池
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor((int) countDownLatchCount, (int) countDownLatchCount, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(10), threadFactory);
            for (int i = 0; i < countDownLatchCount; i++) {
                Test.ResourceLoadSonThread resourceLoadSonThread = new Test.ResourceLoadSonThread(countDownLatch);
                threadPoolExecutor.execute(resourceLoadSonThread);
            }

            try {
                countDownLatch.await();
                System.out.println("所有的资源已加载完成，准备进行测试。。。");

                // 关闭线程池资源
                threadPoolExecutor.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 资源加载子任务线程
     */
    static class ResourceLoadSonThread implements Runnable {
        CountDownLatch countDownLatch;


        public ResourceLoadSonThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + ", 加载资源成功！");
                countDownLatch.countDown();
            }

        }
    }
}
