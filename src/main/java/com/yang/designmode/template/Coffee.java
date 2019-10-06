package com.yang.designmode.template;

public class Coffee extends CaffeineBeverage {

    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    //覆盖钩子方法，我不加糖，那么就不会调用父类中的addCondiments()方法
    @Override
    public boolean customerWantsCondiments() {
        return false;
    }
}
