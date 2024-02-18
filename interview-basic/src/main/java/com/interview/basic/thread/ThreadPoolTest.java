package com.interview.basic.thread;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-27 11:27
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor();

       // poolExecutor.execute(new Thread());
        // private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        // 00000000 00000000 00000000 00000000
        //       线程状态  线程数量
        /*int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        else if (!addWorker(command, false))
            reject(command);*/
    }
}
