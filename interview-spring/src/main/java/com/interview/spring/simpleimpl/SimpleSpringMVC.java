package com.interview.spring.simpleimpl;

import com.interview.spring.simpleimpl.aop.Aop;
import com.interview.spring.simpleimpl.core.BeanContainer;
import com.interview.spring.simpleimpl.ioc.Ioc;
import com.interview.spring.simpleimpl.mvc.server.Server;
import com.interview.spring.simpleimpl.mvc.server.TomcatServer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 02:23
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class SimpleSpringMVC {


    /**
     * 全局配置
     */
    @Getter
    private static Configuration configuration = Configuration.builder().build();

    /**
     * 默认服务器
     */
    @Getter
    private static Server server;

    /**
     * 启动
     */
    public static void run(Class<?> bootClass) {
        run(Configuration.builder().bootClass(bootClass).build());
    }

    /**
     * 启动
     */
    public static void run(Class<?> bootClass, int port) {
        run(Configuration.builder().bootClass(bootClass).serverPort(port).build());
    }

    /**
     * 启动
     */
    public static void run(Configuration configuration) {
        new SimpleSpringMVC().start(configuration);
    }

    /**
     * 初始化
     */
    private void start(Configuration configuration) {
        try {
            SimpleSpringMVC.configuration = configuration;
            String basePackage = configuration.getBootClass().getPackage().getName();
            BeanContainer.getInstance().loadBeans(basePackage);
            //注意Aop必须在Ioc之前执行
            new Aop().doAop();
            new Ioc().doIoc();

            server = new TomcatServer(configuration);
            server.startServer();
        } catch (Exception e) {
            log.error("simpleSpringMVC 启动失败", e);
        }
    }
}
