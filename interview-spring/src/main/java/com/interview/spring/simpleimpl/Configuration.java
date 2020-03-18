package com.interview.spring.simpleimpl;


import lombok.Builder;
import lombok.Getter;

/**
 * 服务器相关配置
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 02:19
 */
@Builder
@Getter
public class Configuration {

    /**
     * 启动类
     */
    private Class<?> bootClass;

    /**
     * 资源目录
     */
    @Builder.Default
    private String resourcePath = "src/main/resources/";

    /**
     * jsp目录
     */
    @Builder.Default
    private String viewPath = "/templates/";

    /**
     * 静态文件目录
     */
    @Builder.Default
    private String assetPath = "/static/";

    /**
     * 端口号
     */
    @Builder.Default
    private int serverPort = 9090;

    /**
     * tomcat docBase目录
     */
    @Builder.Default
    private String docBase = "";

    /**
     * tomcat contextPath目录
     */
    @Builder.Default
    private String contextPath = "";
}