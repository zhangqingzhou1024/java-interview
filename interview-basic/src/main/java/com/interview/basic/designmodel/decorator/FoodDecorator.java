package com.interview.basic.designmodel.decorator;

/**
 * 装饰器
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 23:47
 */
public abstract class FoodDecorator implements Food {

    Food food;
    FoodDecorator(Food food) {
        this.food = food;
    }

    @Override
    public String eat() {
        return this.food.eat();
    }
}