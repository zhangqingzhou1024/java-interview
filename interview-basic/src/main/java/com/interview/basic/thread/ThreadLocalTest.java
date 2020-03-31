package com.interview.basic.thread;

import com.interview.basic.GetTopMin;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-25 21:45
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<GetTopMin> getTopMinThreadLocal = new ThreadLocal<>();

        getTopMinThreadLocal.set(new GetTopMin());
        getTopMinThreadLocal.get();

    }
}
