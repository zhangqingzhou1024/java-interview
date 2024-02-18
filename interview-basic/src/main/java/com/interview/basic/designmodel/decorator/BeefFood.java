package com.interview.basic.designmodel.decorator;

/**
 * 牛肉
 *
 * @author zqz
 * @version 1.0
 * @date 2020-02-28 13:36
 */
public class BeefFood extends FoodDecorator {

    BeefFood(Food food) {
        super(food);
    }

    @Override
    public String eat() {
        food.eat();
        System.out.println("牛肉");
        return "BeefFood";
    }
}
