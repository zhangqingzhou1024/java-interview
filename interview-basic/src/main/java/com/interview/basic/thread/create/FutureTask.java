package com.interview.basic.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-30 10:29
 */
public class FutureTask implements Callable {

    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return "FutureTask";
    }

    public static void main(String[] args) throws Exception {
        java.util.concurrent.FutureTask futureTask = new java.util.concurrent.FutureTask<>(new FutureTask());

        Thread thread = new Thread(futureTask);
        thread.start();

        // 等待结果返回，在没有返回之前去获取，还是同步阻塞状态
        Object o = futureTask.get();

        System.out.println("return val is " + o);
    }
}
