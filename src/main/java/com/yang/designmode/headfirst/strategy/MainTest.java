package com.yang.designmode.headfirst.strategy;

public class MainTest {

    public static void main(String[] args) {
        Duck duck = new ModelDuck();
        duck.setQuackBehavior(new Quack());
        duck.performQuack();
        duck.setFlyBehavior(new FlyWithWings());//动态改变行为
        duck.performFly();
    }
}
