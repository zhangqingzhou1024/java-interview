package com.interview.spring.simpleimpl.testspring;

import com.interview.spring.simpleimpl.annotation.Autowired;
import com.interview.spring.simpleimpl.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:10
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    UserService userService;

    public String getUserName() {
        return userService.getUserName();
    }

    /**
     * 通过aspectj 构造表达式来判断
     *
     * @return
     */
    public String helloForAspect() {
        log.info("Hello Aspectj");

        return "Hello Aspectj";
    }
}
