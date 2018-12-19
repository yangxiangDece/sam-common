package com.yang.design.mode.factory.method;

public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza=null;
        PizzaIngredientFactory pizzaIngredientFactory=new NYPizzaIngredientFactory();
        if("cheese".equals(type)){
            pizza=new CheesePizza(pizzaIngredientFactory);
            pizza.setName("ZJG Style Cheese Pizza");
        } else if ("clam".equals(type)) {
            pizza=new ClamPizza(pizzaIngredientFactory);
            pizza.setName("ZJG Style Clam Pizza");
        }
        return pizza;
    }
}
