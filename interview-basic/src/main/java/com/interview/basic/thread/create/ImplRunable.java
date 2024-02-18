package com.interview.basic.thread.create;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-30 10:28
 */
public class ImplRunable implements Runnable {
    @Override
    public void run() {
        System.out.println("ImplRunable");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ImplRunable());
        thread.start();
    }
}
