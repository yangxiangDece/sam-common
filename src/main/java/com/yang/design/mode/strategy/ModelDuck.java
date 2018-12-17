package com.yang.design.mode.strategy;

public class ModelDuck extends Duck {

    //定义MallardDuck的默认行为实现，也可以通过父类的setter方法，动态设置行为
    public ModelDuck() {
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    void display() {
        System.out.println("I'm a model duck!");
    }
}
