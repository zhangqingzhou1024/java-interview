package com.zqz.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.Field;

/**
 * @author zqz
 * @date 2024-03-14 1:46
 */
@Component
public class MyValueBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(MyValue.class)) {
                String value = field.getAnnotation(MyValue.class).value();

                try {
                    field.set(bean, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
