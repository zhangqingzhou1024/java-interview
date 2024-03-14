package com.zqz.service;

import com.spring.*;

/**
 * @author zqz
 * @date 2024-03-13 23:49
 */
//@Component("userService")
@Component()
//@Scope("singleton")
//@Scope("prototype")
public class UserService implements UserInterface, InitializingBean, BeanNameAware, ApplicationContextAware {

    String beanName;
    LiziyuanApplicationContext applicationContext;

    @Autowired
    OrderService orderService;

    @MyValue("abcd")
    String value;

    public void test() {

        System.out.println("test 方法执行！！！");
        System.out.println("orderService ==>" + orderService);
        System.out.println("value ==>" + value);
        System.out.println("beanName ==>" + beanName);
        System.out.println("applicationContext ==>" + applicationContext);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean ==> afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(LiziyuanApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
