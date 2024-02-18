package com.interview.spring.simpleimpl.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 代理切点类
 * 这个类中有三个变量：pointcutParser,expression,pointcutExpression。
 * <p>
 * 其中expression是String类型，用于存放我们要设定的aspectj表达式，
 * 比如execution(* com.zbw.*.service..*Impl.*(..))这样的。
 * <p>
 * pointcutParser和pointcutExpression就是aspectj里面的类了，
 * pointcutParser用于根据expression中的表达式创建pointcutExpression表达式解析器。
 * 而pointcutExpression可以用来判断方法或者类是否匹配表达式。
 * <p>
 * 这个类中最主要的两个方法就matches(Class<?> targetClass)和matches(Method method)，
 * 这两个方法分别用于判定目标的类和方法是否匹配expression中的aspectj表达式。
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 21:06
 */
public class ProxyPointcut {

    /**
     * 切点解析器
     */
    private PointcutParser pointcutParser;

    /**
     * (AspectJ)表达式
     */
    private String expression;

    /**
     * 表达式解析器
     */
    private PointcutExpression pointcutExpression;

    /**
     * AspectJ语法集合
     */
    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public ProxyPointcut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public ProxyPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        pointcutParser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }

    /**
     * Class是否匹配切点表达式
     */
    public boolean matches(Class<?> targetClass) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     * Method是否匹配切点表达式
     */
    public boolean matches(Method method) {
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        }
        // TODO:还有个情况不判断了！详情见org.springframework.aop.aspectj.AspectJExpressionPointcut@matches()方法
        return false;
    }

    /**
     * 初始化切点解析器
     */
    private void checkReadyToMatch() {
        if (null == pointcutExpression) {
            pointcutExpression = pointcutParser.parsePointcutExpression(expression);
        }
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
