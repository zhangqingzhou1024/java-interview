package com.interview.spring.simpleimpl.annotation.mvc;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * http请求路径
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 01:32
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * 请求路径
     */
    String value() default "";

    /**
     * 请求方法
     */
    RequestMethod method() default RequestMethod.GET;
}
