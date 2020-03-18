package com.interview.spring.simpleimpl.aop.advice;

import java.lang.reflect.Method;

/**
 * 返回通知接口
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:39
 */
public interface AfterReturningAdvice extends Advice {
    /**
     * 返回后方法
     */
    void afterReturning(Class<?> clz, Object returnValue, Method method, Object[] args) throws Throwable;
}
