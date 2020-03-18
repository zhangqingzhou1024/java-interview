package com.interview.spring.simpleimpl.annotation;

import java.lang.annotation.*;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 01:09
 */
// Service注解，用于标记Service层的组件
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
