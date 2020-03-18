package com.interview.basic.io.bio.decoratormodel;

/**
 * 功能组件
 * @author zqz
 * @version 1.0
 * @date 2020-01-18 00:16
 */
public class Componet implements Decorator{
    @Override
    public void doSomeThing() {
        System.out.println("功能A");
    }
}
