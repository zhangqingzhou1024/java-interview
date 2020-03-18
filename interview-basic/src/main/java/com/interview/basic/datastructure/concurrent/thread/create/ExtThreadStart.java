package com.interview.basic.datastructure.concurrent.thread.create;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-10 20:32
 */
public class ExtThreadStart {

    public static void main(String[] args) {
        ExThread exThread = new ExThread();
        exThread.start();
    }

    static class  ExThread extends Thread {

        @Override
        public void run() {
            System.out.println("ExThread's run method ");
        }
    }
}
