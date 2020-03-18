package com.interview.source.spring.test;

import com.interview.source.spring.test.BeanTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-02-07 16:46
 */
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanTest.class);
        BeanTest bean = annotationConfigApplicationContext.getBean(BeanTest.class);

        System.out.println(bean.toString());
    }
}
