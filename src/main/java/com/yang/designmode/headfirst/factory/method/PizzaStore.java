package com.yang.designmode.headfirst.factory.method;

/**
 * 抽象创建者类（creator），定义了一个抽象的工厂方法，让子类实现此方法制造产品
 */
public abstract class PizzaStore {

    /**
     * 创建者通常会包含依赖于抽象产品的代码，而这些抽象产品由子类制造。
     * 创建者不需要真的知道制造哪种具体产品
     */
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    //实例化比萨的责任被移到一个方法中，此方法如同是一个工厂
    protected abstract Pizza createPizza(String type);
}
