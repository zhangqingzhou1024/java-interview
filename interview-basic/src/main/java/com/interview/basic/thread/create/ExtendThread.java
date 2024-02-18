package com.interview.basic.thread.create;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-30 10:27
 */
public class ExtendThread extends Thread {

    @Override
    public void run() {
        System.out.println("ExtendThread");

    }

    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread();
        extendThread.start();
    }
}
