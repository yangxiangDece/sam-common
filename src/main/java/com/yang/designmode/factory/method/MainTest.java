package com.yang.designmode.factory.method;

public class MainTest {

    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("NYPizzaStore:" + pizza.getName());

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("ChicagoStore:" + pizza.getName());
    }
}
