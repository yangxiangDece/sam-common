package com.yang.designmode.factory.abstracts;

public class NYPizzaStore extends PizzaStore {

    //使用这个工厂方法来创建产品，通过传入各种不同的工厂，可以制作出各种不同的产品，但是客户端代码始终没变
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory pizzaIngredientFactory = new NYPizzaIngredientFactory();
        if ("cheese".equals(type)) {
            pizza = new CheesePizza(pizzaIngredientFactory);
            pizza.setName("ZJG Style Cheese Pizza");
        } else if ("clam".equals(type)) {
            pizza = new ClamPizza(pizzaIngredientFactory);
            pizza.setName("ZJG Style Clam Pizza");
        }
        return pizza;
    }
}
