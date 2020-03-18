package com.interview.spring.simpleimpl.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 通知链
 * 多个切面进行织入的情况
 * 由于要实现多个通知类链式执行的功能，这个类就是代替之前的ProxyAdvisor来生产代理类，
 * 并且通过doAdviceChain()方法执行具体的切面方法以及目标代理类的方法。
 * <p>
 * 在最初设计这个方法的时候，我想的是直接for循环proxyList这个属性里的ProxyAdvisor，
 * 然后一个个执行对应的Advice方法不就行了，后来发现这是不行的。因为在AOP的功能设计里，多个切面的执行顺序是一种'先入后出'的顺序。比如说有两个切面Aspect1和Aspect2，那么他们的执行顺序应该是Aspect1@before()->Aspect2@before()->targetClass@method()->Aspect2@after()->Aspect1@after()，先执行的Aspect1@before()方法要在最后执行Aspect1@after()。
 * <p>
 * 要实现'先入后出'的功能通常有两种实现方式，一是借助栈这个数据结构，
 * 二是用递归的方式，这里我们用递归的方式实现。
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 22:00
 */
public class AdviceChain {

    /**
     * 目标类
     */
    @Getter
    private final Class<?> targetClass;
    /**
     * 目标实例
     */
    @Getter
    private final Object target;
    /**
     * 目标方法
     */
    @Getter
    private final Method method;
    /**
     * 目标方法参数
     */
    @Getter
    private final Object[] args;
    /**
     * 代理方法
     */
    private final MethodProxy methodProxy;
    /**
     * 代理通知列
     */
    private List<ProxyAdvisor> proxyList;
    /**
     * 代理通知列index
     */
    private int adviceIndex = 0;

    public AdviceChain(Class<?> targetClass, Object target, Method method, Object[] args, MethodProxy methodProxy, List<ProxyAdvisor> proxyList) {
        this.targetClass = targetClass;
        this.target = target;
        this.method = method;
        this.args = args;
        this.methodProxy = methodProxy;
        this.proxyList = proxyList;
    }

    /**
     * 递归执行 执行代理通知列
     */
    public Object doAdviceChain() throws Throwable {

        Object result;
        while (adviceIndex < proxyList.size()
                && !proxyList.get(adviceIndex).getPointcut().matches(method)) {
            //如果当前方法不匹配切点,则略过该代理通知类
            adviceIndex++;
        }
        if (adviceIndex < proxyList.size()) {
            // doProxy() -> doAdviceChain() -> doProxy(); 递归
            result = proxyList.get(adviceIndex++).doProxy(this);
        } else {
            result = methodProxy.invokeSuper(target, args);
        }
        return result;
    }

}
