package com.spring;

/**
 * @author zqz
 * @date 2024-03-14 1:09
 */
public interface BeanPostProcessor {

    /**
     * 初始化前
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) /*throws BeansException */ {
        return bean;
    }


    /**
     * 初始化后
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) /*throws BeansException*/ {
        return bean;
    }
}
