package com.interview.basic.thread.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat 时间转化
 * 参考：https://blog.csdn.net/qq_35606010/article/details/100087705
 *
 * @author zqz
 * @version 1.0
 * @date 2021-03-20 16:56
 */
public class SimpleDateFormatTest01 {

    // (1)创建单例实例
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void test1() {
        // (2)创建多个线程，并启动
        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // (3)使用单例日期实例解析文本
                       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                       // System.out.println(Thread.currentThread().hashCode());
                        System.out.println(sdf.parse("2021-03-20 16:56:27"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();// (4)启动线程
        }

    }

    public static void main(String[] args) {

        test1();

    }
}
