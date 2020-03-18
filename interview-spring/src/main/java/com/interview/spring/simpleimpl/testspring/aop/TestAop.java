package com.interview.spring.simpleimpl.testspring.aop;

import com.interview.spring.simpleimpl.aop.Aop;
import com.interview.spring.simpleimpl.core.BeanContainer;
import com.interview.spring.simpleimpl.ioc.Ioc;
import com.interview.spring.simpleimpl.testspring.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 17:32
 */
@Slf4j
public class TestAop {

    @Test
    public void doAop() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.interview.spring");
        new Aop().doAop();
        new Ioc().doIoc();
        UserController controller = (UserController) beanContainer.getBean(UserController.class);
        String userName = controller.getUserName();
        System.out.println(userName);
        log.info("username ->" + userName);

        String helloForAspect = controller.helloForAspect();
        System.out.println("helloForAspect ->" + helloForAspect);


    }
}
