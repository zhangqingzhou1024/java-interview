package com.interview.basic.designmodel.factory;

/**
 * @author zqz
 * @date 2024-02-16 0:29
 */
public class SingletonFactory {
    private static volatile SingletonFactory instance;

    public static SingletonFactory getInstance(){
        if(instance == null){
            synchronized (SingletonFactory.class){
                if(instance == null){
                    /**
                     * 1、开辟内存空间
                     * 2、对象初始化
                     * 3、instance指向内存空间
                     * ---------指令重排序--------
                     * 1、开辟内存空间
                     * 3、instance指向内存空间
                     * 2、对象初始化
                     */
                    instance = new SingletonFactory();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        SingletonFactory instance = SingletonFactory.getInstance();
        System.out.println(instance);
    }
}
