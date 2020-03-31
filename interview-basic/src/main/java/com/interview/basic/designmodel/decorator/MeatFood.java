package com.interview.basic.designmodel.decorator;

/**
 * 猪肉
 *
 * @author zqz
 * @version 1.0
 * @date 2020-02-28 13:38
 */
public class MeatFood extends FoodDecorator {
    MeatFood(Food food) {
        super(food);
    }

    @Override
    public String eat() {
        food.eat();
        System.out.println("猪肉");
        return "MeatFood";
    }
}
