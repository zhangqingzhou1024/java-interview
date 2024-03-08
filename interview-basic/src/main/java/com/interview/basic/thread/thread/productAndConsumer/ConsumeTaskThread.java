package com.interview.basic.thread.thread.productAndConsumer;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-07 22:54
 */
public class ConsumeTaskThread implements Runnable {

    private Object lock;

    public ConsumeTaskThread(Object lock) {
        this.lock = lock;
    }


    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    TaskQueueManager<TaskMsg> queueManager = TaskQueueManager.getInstance();
                    if (queueManager.getSize() <= 0) {
                        System.out.println("队列长度为0，等待生产...");
                        lock.wait();
                    }
                    TaskMsg taskMsg = queueManager.pollMsg();
                    System.out.println("消费任务 ->" + taskMsg.getTaskId());
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
