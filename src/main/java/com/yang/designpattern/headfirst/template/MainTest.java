package com.yang.designpattern.headfirst.template;

public class MainTest {

    public static void main(String[] args) {
        CaffeineBeverage beverage = new Tea();
        beverage.prepareRecipe();
    }
}
