package com.interview.basic.designmodel.decorator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 23:50
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        FoodDecorator foodDecorator = new BeefFood(new MeatFood(new RiceFood()));

        String eat = foodDecorator.eat();
        //System.out.println(eat);

        String filePath = "";
       // new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    }
}
