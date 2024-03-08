package com.interview.basic.thread.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-10 20:44
 */
public class ImpCallableStart {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TaskImpCallable taskImpCallable = new TaskImpCallable();
        FutureTask<String> futureTask = new FutureTask<String>(taskImpCallable);

        Thread thread = new Thread(futureTask);
        thread.start();

        String s = futureTask.get();

        System.out.println("callable result is " + s);

    }

    /**
     * callable接口，带返回值
     */
    static class TaskImpCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000L);
                System.out.println("TaskImpCallable is " + i);
            }
            return "result";
        }
    }
}
