package com.yang.design.mode.factory.method;

public class ZJGPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza=null;
        PizzaIngredientFactory pizzaIngredientFactory=new ZJGPizzaIngredientFactory();
        if("cheese".equals(type)){
            pizza=new CheesePizza(pizzaIngredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if ("clam".equals(type)) {
            pizza=new ClamPizza(pizzaIngredientFactory);
            pizza.setName("New York Style Clam Pizza");
        }
        return pizza;
    }
}
