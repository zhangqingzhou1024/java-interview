package com.interview.spring.simpleimpl.aop.advice;

import java.lang.reflect.Method;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:34
 */
public interface MethodBeforeAdvice extends Advice {
    /**
     * 前置方法
     */
    void before(Class<?> clz, Method method, Object[] args) throws Throwable;
}
