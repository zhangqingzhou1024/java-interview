package com.interview.source.spring.ioc.jdkproxy;

/**
 * 真正执行对象
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 19:25
 */
public class RealOperateObj implements TargetProxyInterface {
    @Override
    public String excute(String val) {

        return "RealOperateObj ——>" + val;
    }
}
