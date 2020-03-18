package com.interview.basic.datastructure.concurrent.thread.productAndConsumer;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-07 23:05
 */
public class Test {

    public static void main(String[] args) {
        Object lock = new Object();

        ProductTaskThread objectProductTaskThread = new ProductTaskThread(lock);
        ConsumeTaskThread objectConsumeTaskThread = new ConsumeTaskThread(lock);

        Thread thread = new Thread(objectProductTaskThread);
        Thread thread1 = new Thread(objectConsumeTaskThread);

        thread.start();

        thread1.start();

    }
}
