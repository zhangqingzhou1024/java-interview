package com.interview.spring.simpleimpl.annotation;

import java.lang.annotation.*;

/**
 * 切面 标记注解
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 目标代理类的范围
     */
    // Class<? extends Annotation> target();

    /**
     * 存储表达式如：
     * execution(* com.zbw.*.service..*Impl.*(..))
     *
     * @return
     */
    String pointcut() default "";
}
