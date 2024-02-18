package com.interview.spring.simpleimpl.annotation;

import java.lang.annotation.*;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 01:08
 */
// Controller注解，用于标记Controller层的组件
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
