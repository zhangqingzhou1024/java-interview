package com.interview.spring.simpleimpl.aop.advice;

import java.lang.reflect.Method;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:41
 */
public interface ThrowsAdvice extends Advice {
    /**
     * 异常方法
     */
    void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e);
}
