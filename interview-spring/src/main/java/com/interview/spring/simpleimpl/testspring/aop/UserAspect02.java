package com.interview.spring.simpleimpl.testspring.aop;

import com.interview.spring.simpleimpl.annotation.Aspect;
import com.interview.spring.simpleimpl.annotation.Order;
import com.interview.spring.simpleimpl.aop.advice.AroundAdvice;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * 实现具体的切面
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 17:27
 */
@Slf4j
@Order(2)
//@Aspect(pointcut = "execution(* com.interview.spring.simpleimpl.testspring.UserController.*(..))")
@Aspect(pointcut = "execution(* com.interview.spring.simpleimpl.testspring.UserController.helloForAspect(..))")
public class UserAspect02 implements AroundAdvice {
    @Override
    public void afterReturning(Class<?> clz, Object returnValue, Method method, Object[] args) throws Throwable {
        log.info("afterReturning  UserAspect02 ----> class: {}, method: {}", clz.getName(), method.getName());
    }

    @Override
    public void before(Class<?> clz, Method method, Object[] args) throws Throwable {
        log.info("Before  UserAspect02 ----> class: {}, method: {}", clz.getName(), method.getName());
    }

    @Override
    public void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e) {
        log.info("afterThrowing  UserAspect02 ----> class: {}, method: {}", clz.getName(), method.getName());

    }
}
