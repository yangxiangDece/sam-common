package com.yang.design.mode.factory;

/**
 * 具体工厂创建者（ConcreteCreator），createPizza(String type)就是工厂方法，能够实际产生产品
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if("cheese".equals(type)){
            return new NYStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            return new NYStyleVeggiePizza();
        }
        return null;
    }
}
