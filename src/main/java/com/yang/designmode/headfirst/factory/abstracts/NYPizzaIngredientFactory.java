package com.yang.designmode.headfirst.factory.abstracts;

import com.yang.designmode.headfirst.factory.abstracts.material.*;

/**
 * 具体工厂类
 * <p>
 * 这个具体工厂类实现不同的产品家族，需要创建一个产品，客户只需要使用其中一个工厂而完全不需要实例化任何产品对象
 * <p>
 * 纽约比萨工厂类
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
