package com.interview.spring.simpleimpl.annotation;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 00:43
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * aop顺序
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    /**
     * aop顺序,值越大越先执行
     */
    int value() default 0;
}
