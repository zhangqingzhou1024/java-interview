package com.interview.spring.simpleimpl.aop;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:53
 */

import com.interview.spring.simpleimpl.annotation.Aspect;
import com.interview.spring.simpleimpl.annotation.Order;
import com.interview.spring.simpleimpl.aop.advice.Advice;
import com.interview.spring.simpleimpl.core.BeanContainer;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Aop执行器
 */
@Slf4j
public class Aop {
    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    public Aop() {
        beanContainer = BeanContainer.getInstance();
    }

    public void doAop() {
        // 获取容器内Advice接口下的所有类，并遍历
        List<ProxyAdvisor> proxyList = beanContainer.getClassesBySuper(Advice.class)
                .stream()
                // 判断这个类的注解是不是Aspect，如果是的化继续执行（UserAspect）
                .filter(clz -> clz.isAnnotationPresent(Aspect.class))
                // 类型发生转换，需在此判断父类或接口或注解是否正确
                // 转换的目的是把表达式信息封装在一起
                .map(this::createProxyAdvisor)
                .collect(Collectors.toList());


        // 从所有类型中进一步过滤
        beanContainer.getClasses()
                .stream()
                // 判断target 是否为Advice的子类或实现
                .filter(target -> !Advice.class.isAssignableFrom(target))
                .filter(target -> !target.isAnnotationPresent(Aspect.class))
                .forEach(target -> {
                    List<ProxyAdvisor> matchProxies = createMatchProxies(proxyList, target);
                    if (matchProxies.size() > 0) {
                        Object proxyBean = ProxyCreator.createProxy(target, matchProxies);
                        beanContainer.addBean(target, proxyBean);
                    }
                });
    }

    /**
     * 通过Aspect切面类创建代理通知类
     */
    private ProxyAdvisor createProxyAdvisor(Class<?> aspectClass) {
        int order = 0;
        if (aspectClass.isAnnotationPresent(Order.class)) {
            order = aspectClass.getAnnotation(Order.class).value();
        }
        String expression = aspectClass.getAnnotation(Aspect.class).pointcut();
        ProxyPointcut proxyPointcut = new ProxyPointcut();
        proxyPointcut.setExpression(expression);
        Advice advice = (Advice) beanContainer.getBean(aspectClass);
        return new ProxyAdvisor(advice, proxyPointcut, order);
    }

    /**
     * 获取目标类匹配的代理通知列表
     */
    private List<ProxyAdvisor> createMatchProxies(List<ProxyAdvisor> proxyList, Class<?> targetClass) {
        Object targetBean = beanContainer.getBean(targetClass);
        return proxyList
                .stream()
                .filter(advisor -> advisor.getPointcut().matches(targetBean.getClass()))
                .sorted(Comparator.comparingInt(ProxyAdvisor::getOrder))
                .collect(Collectors.toList());
    }
}
