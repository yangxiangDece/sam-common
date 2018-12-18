package com.yang.design.mode.factory;

public abstract class PizzaStore {

    public Pizza orderPizza(String type){
        Pizza pizza=createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    //实例化比萨的责任被移到一个方法中，此方法如同是一个工厂
    protected abstract Pizza createPizza(String type);
}
