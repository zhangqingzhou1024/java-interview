package com.interview.source.spring.ioc.jdkproxy;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 20:23
 */
public class TestProxy {

    @Test
    public void usedProxy() {

        RealOperateObj realOperateObj = new RealOperateObj();
        InvocationHandler myInvocationHandler = new MyInvocationHandler(realOperateObj);

        ClassLoader loader = realOperateObj.getClass().getClassLoader();
        Class[] interfaces = realOperateObj.getClass().getInterfaces();
        /**
         * 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
         */
        TargetProxyInterface proxyInterface = (TargetProxyInterface) Proxy.newProxyInstance(loader, interfaces, myInvocationHandler);

        System.out.println("动态代理对象的类型：" + proxyInterface.getClass().getName());

        String hello = proxyInterface.excute("hello");
        System.out.println(hello);

        createProxyClassFile();

    }

    /**
     * 把代理生产的对象，持久化下。方便查看
     */
    private static void createProxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{RealOperateObj.class});
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            System.out.println((new File("hello")).getAbsolutePath());
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
