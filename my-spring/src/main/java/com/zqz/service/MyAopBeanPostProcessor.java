package com.zqz.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zqz
 * @date 2024-03-14 1:37
 */
@Component
public class MyAopBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // 动态代理， AOP

        if (beanName.equals("userService")) {
            System.out.println("MyAopBeanPostProcessor");
            Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 切面逻辑 before
                    System.out.println("切面逻辑 before");

                    // 切面
                    return method.invoke(bean, args);
                }
            });

            return proxy;
        }
        return bean;
    }
}
