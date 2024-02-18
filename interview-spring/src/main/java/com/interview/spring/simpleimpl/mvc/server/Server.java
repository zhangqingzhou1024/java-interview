package com.interview.spring.simpleimpl.mvc.server;


/**
 * 服务器 interface
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 02:21
 */
public interface Server {
    /**
     * 启动服务器
     */
    void startServer() throws Exception;

    /**
     * 停止服务器
     */
    void stopServer() throws Exception;
}

