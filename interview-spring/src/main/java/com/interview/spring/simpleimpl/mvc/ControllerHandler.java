package com.interview.spring.simpleimpl.mvc;

import com.interview.spring.simpleimpl.annotation.mvc.RequestMapping;
import com.interview.spring.simpleimpl.annotation.mvc.RequestMethod;
import com.interview.spring.simpleimpl.annotation.mvc.RequestParam;
import com.interview.spring.simpleimpl.core.BeanContainer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求处理器
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 01:49
 */
@Slf4j
public class ControllerHandler {

    // 映射容器
    private Map<PathInfo, ControllerInfo> pathControllerMap = new ConcurrentHashMap<>();

    // bean容器
    private BeanContainer beanContainer;

    public ControllerHandler() {
        beanContainer = BeanContainer.getInstance();
        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(RequestMapping.class);
        for (Class<?> clz : classSet) {
            putPathController(clz);
        }
    }

    /**
     * 获取ControllerInfo
     */
    public ControllerInfo getController(String requestMethod, String requestPath) {
        PathInfo pathInfo = new PathInfo(requestMethod, requestPath);
        return pathControllerMap.get(pathInfo);
    }

    /**
     * 添加信息到requestControllerMap中
     */
    private void putPathController(Class<?> clz) {
        RequestMapping controllerRequest = clz.getAnnotation(RequestMapping.class);
        String basePath = controllerRequest.value();
        Method[] controllerMethods = clz.getDeclaredMethods();
        // 1. 遍历Controller中的方法
        for (Method method : controllerMethods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                // 2. 获取这个方法的参数名字和参数类型
                Map<String, Class<?>> params = new HashMap<>();
                for (Parameter methodParam : method.getParameters()) {
                    RequestParam requestParam = methodParam.getAnnotation(RequestParam.class);
                    if (null == requestParam) {
                        throw new RuntimeException("必须有RequestParam指定的参数名");
                    }
                    params.put(requestParam.value(), methodParam.getType());
                }
                // 3. 获取这个方法上的RequestMapping注解
                RequestMapping methodRequest = method.getAnnotation(RequestMapping.class);
                String methodPath = methodRequest.value();
                RequestMethod requestMethod = methodRequest.method();
                PathInfo pathInfo = new PathInfo(requestMethod.toString(), basePath + methodPath);
                if (pathControllerMap.containsKey(pathInfo)) {
                    log.error("url:{} 重复注册", pathInfo.getHttpPath());
                    throw new RuntimeException("url重复注册");
                }
                // 4. 生成ControllerInfo并存入Map中
                ControllerInfo controllerInfo = new ControllerInfo(clz, method, params);
                this.pathControllerMap.put(pathInfo, controllerInfo);
                log.info("Add Controller RequestMethod:{}, RequestPath:{}, Controller:{}, Method:{}",
                        pathInfo.getHttpMethod(), pathInfo.getHttpPath(),
                        controllerInfo.getControllerClass().getName(), controllerInfo.getInvokeMethod().getName());
            }
        }
    }
}