package com.interview.basic.thread.aqs;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @author zqz
 * @date 2024-02-18 11:36
 */
public abstract class AQS {
    // 用volatile修饰的可用资源状态量， 后面配合 CAS(compareAndSwap)
    private volatile int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

   public void compareAndSwapState(){

   }

    // 对应 AQS ConditionObject
    public class CndObject implements Condition, java.io.Serializable {
        private static final long serialVersionUID = 1173984872572414699L;

        @Override
        public void await() throws InterruptedException {

        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {

        }

        @Override
        public void signalAll() {

        }
    }
}
