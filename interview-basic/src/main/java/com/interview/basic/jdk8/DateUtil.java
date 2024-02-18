package com.interview.basic.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-25 22:14
 */
public class DateUtil {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        LocalDateTime now1 = LocalDateTime.now();

        //线程安全
        //now1.format()
        System.out.println(now);
        System.out.println(now1);
    }
}
