package com.interview.spring.simpleimpl.testspring.ioc;

import com.interview.spring.simpleimpl.core.BeanContainer;
import com.interview.spring.simpleimpl.ioc.Ioc;
import com.interview.spring.simpleimpl.testspring.UserController;
import org.junit.Test;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 14:14
 */
public class TestIoc {

    @Test
    public void getUserName() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.interview.spring.testspring");
        new Ioc().doIoc();
        UserController controller = (UserController) beanContainer.getBean(UserController.class);
        String userName = controller.getUserName();
        System.out.println(userName);
    }
}
