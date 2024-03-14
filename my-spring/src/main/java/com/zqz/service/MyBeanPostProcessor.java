package com.zqz.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

/**
 * @author zqz
 * @date 2024-03-14 1:37
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessor ==> "+beanName);
        return bean;
    }
}
