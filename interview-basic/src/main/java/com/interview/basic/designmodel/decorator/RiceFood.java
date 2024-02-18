package com.interview.basic.designmodel.decorator;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 23:58
 */
public class RiceFood implements Food {


    @Override
    public String eat() {
        System.out.println("米饭");
        return "米饭";
    }
}
