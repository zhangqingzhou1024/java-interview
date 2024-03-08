package com.interview.basic.jvm.gc;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-08 21:25
 */
public class DeadLockTest {

    private static final Object lockA = new Object();

    private static final Object lockB = new Object();


    public static void main(String[] args) {
        DeadLockThreadA deadLockThreadA = new DeadLockThreadA();
        DeadLockThreadB deadLockThreadB = new DeadLockThreadB();

        deadLockThreadA.start();
        deadLockThreadB.start();
    }


    static class DeadLockThreadA extends Thread{

        @Override
        public void run() {
            synchronized (lockB  ){
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){

                }
                System.out.println("DeadLockThreadA -> run");
            }
        }
    }

    static class DeadLockThreadB extends Thread{

        @Override
        public void run() {
            synchronized (lockA){
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){

                }
                System.out.println("DeadLockThreadB -> run");
            }
        }
    }

}
