package com.interview.basic.thread.thread.productAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者消费者模型中的任务队列容器
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-07 22:25
 */
public class TaskQueueManager<T> {

    private BlockingQueue<T> taskQueue = null;

    private static TaskQueueManager queManager;

    private int size = 0;

    private int max_num = 10;

    /**
     * 构造方法,初始化队列
     */
    private TaskQueueManager() {
        init();
    }

    /**
     * 单例方法初始化对象
     *
     * @return QueueManager
     */
    public static synchronized TaskQueueManager getInstance() {
        if (queManager == null) {
            queManager = new TaskQueueManager();
        }
        return queManager;
    }

    /**
     * 初始化队列
     * 有界 阻塞 队列
     */
    private void init() {
        taskQueue = new LinkedBlockingQueue<>(max_num);

    }

    /**
     * 添加一个元素,如果队列满，则阻塞
     *
     * @param task
     */
    public void putMsg(T task) {
        try {
            size++;
            taskQueue.put(task);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 移除并返回队列头部的元素 如果队列为空，则阻塞
     *
     * @return task
     */
    public T takeMsg() {
        T task = null;
        try {
            task = taskQueue.take();
            size--;
        } catch (InterruptedException e) {
        }
        return task;
    }

    /**
     * 移除并返回队列头部的元素 如果队列为空，则返回  null
     *
     * @return message
     */
    public T pollMsg() {
        T task = null;
        try {
            task = taskQueue.poll();
            size--;
        } catch (Exception e) {
        }
        return task;
    }

    /**
     * @return queue's size
     */
    public int getQueSize() {
        return taskQueue.size();
    }

    /**
     * 队列长度
     *
     * @return
     */
    public int getSize() {

        return this.size;
    }

    /**
     * 队列最大长度
     *
     * @return
     */
    public int getMaxNum() {

        return this.max_num;
    }

    /**
     * clear the queue
     */
    public void clearQue() {
        taskQueue.clear();
    }

    @Override
    public String toString() {
        return "QueueManager [taskQueue=" + taskQueue + "]";
    }


}
