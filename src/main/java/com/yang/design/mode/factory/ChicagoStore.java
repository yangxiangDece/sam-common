package com.yang.design.mode.factory;

public class ChicagoStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if("cheese".equals(type)){
            return new ChicagoStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            return new ChicagoStyleVeggiePizza();
        }
        return null;
    }
}
