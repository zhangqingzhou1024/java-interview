package com.interview.basic.datastructure.concurrent.thread.create;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-10 20:36
 */
public class ImpRunableStart {
    public static void main(String[] args) {

        ImpfRunable ImpfRunable = new ImpfRunable();
        Thread thread = new Thread(ImpfRunable);
        thread.start();

    }

    static class ImpfRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("IntfRunableStart's run method ");
        }
    }
}
