package com.interview.source.spring.ioc.jdkproxy;

/**
 * 需要动态代理的目标接口
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 19:22
 */
public interface TargetProxyInterface {
    /**
     * 执行方法
     *
     * @param val
     * @return
     */
    public String excute(String val);


}
