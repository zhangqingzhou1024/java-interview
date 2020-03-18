package com.interview.spring.simpleimpl.mvc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * ControllerInfo 存储Controller相关信息
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 01:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerInfo {
    /**
     * controller类
     */
    private Class<?> controllerClass;

    /**
     * 执行的方法
     */
    private Method invokeMethod;

    /**
     * 方法参数别名对应参数类型
     */
    private Map<String, Class<?>> methodParameter;
}
