package com.interview.spring.simpleimpl.annotation.mvc;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 01:34
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求的方法参数名
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    /**
     * 方法参数别名
     */
    String value() default "";

    /**
     * 是否必传
     */
    boolean required() default true;
}
