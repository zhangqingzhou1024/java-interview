package com.zqz;

import com.zqz.service.UserInterface;
import com.zqz.service.UserService;
import com.spring.LiziyuanApplicationContext;

/**
 * @author zqz
 * @date 2024-03-13 23:49
 */
public class Test {

    public static void main(String[] args) {
        LiziyuanApplicationContext applicationContext = new LiziyuanApplicationContext(AppConfig.class);

       // UserService userService = (UserService) applicationContext.getBean("userService");
        UserInterface userService = (UserInterface) applicationContext.getBean("userService");

        userService.test();

        System.out.println(userService);
    }
}
