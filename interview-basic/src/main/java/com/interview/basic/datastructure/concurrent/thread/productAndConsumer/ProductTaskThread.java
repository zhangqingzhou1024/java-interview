package com.interview.basic.datastructure.concurrent.thread.productAndConsumer;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-07 22:48
 */
public class ProductTaskThread implements Runnable {

    private Object lock = null;

    public ProductTaskThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (lock) {
                try {
                    TaskQueueManager<TaskMsg> queueManager = TaskQueueManager.getInstance();
                    int size = queueManager.getSize();
                    // 满了
                    if (size >= queueManager.getMaxNum()) {
                        System.out.println("队列已满，等待消费...");
                        lock.wait();
                    }

                    // 不满
                    TaskMsg taskMsg = new TaskMsg();
                    taskMsg.setTaskId(System.currentTimeMillis());
                    queueManager.putMsg(taskMsg);
                    System.out.println("生产任务->"+taskMsg.getTaskId());
                    lock.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
