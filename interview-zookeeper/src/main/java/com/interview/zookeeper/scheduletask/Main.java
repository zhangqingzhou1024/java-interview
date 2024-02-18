package com.interview.zookeeper.scheduletask;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 17:40
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
       IntStream.rangeClosed(1,5)
               .mapToObj(index -> "机器"+index)
               .map(TaskThread::new)
               .map(task ->(Runnable)()-> task.doTask())
               .map(Thread::new)
               .forEach(Thread::start);

        TimeUnit.SECONDS.sleep(30);
    }
}
