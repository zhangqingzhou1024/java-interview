package com.interview.basic.thread.threadlocal;

import java.text.DateFormat;

/**
 * ThreadLocal 的原理
 * 可参考：https://www.zhihu.com/question/341005993
 * @author zqz
 * @version 1.0
 * @date 2021-03-20 17:28
 */
public class ThreadLocalDesign {
    public static ThreadLocal<String> strThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        strThreadLocal.set("this user is zqz");
        String userName = strThreadLocal.get();
        System.out.println(userName);

        BizService bizService = new BizService();
        bizService.processData();

        // 一、线程副本/线程内变量/线程数据隔离是如何实现的呢？如果让我们自己设计，该如何实现？
        /**
         * 思考并讨论5分钟
         * 1.
         * 2.
         */

        //二、 看ThreadLocal是如何实现的 & 为什么要这样设计？
        // https://blog.csdn.net/qq_34218892/article/details/80570472

        //三、 使用过程中会有什么坑？


        strThreadLocal.remove();
    }
}
