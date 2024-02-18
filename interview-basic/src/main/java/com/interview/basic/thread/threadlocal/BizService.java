package com.interview.basic.thread.threadlocal;

/**
 * 业务处理层
 *
 * @author zqz
 * @version 1.0
 * @date 2021-03-20 17:31
 */
public class BizService {

    public void processData() {
        String userInfo = ThreadLocalDesign.strThreadLocal.get();
        System.out.println("BizService.processData get userInfo is" + userInfo);
    }
}
