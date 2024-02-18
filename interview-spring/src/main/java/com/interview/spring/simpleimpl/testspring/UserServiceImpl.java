package com.interview.spring.simpleimpl.testspring;

import com.interview.spring.simpleimpl.annotation.Service;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserName() {
        return "hello world";
    }
}
