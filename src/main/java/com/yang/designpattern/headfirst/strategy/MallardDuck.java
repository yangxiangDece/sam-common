package com.yang.designpattern.headfirst.strategy;

/**
 * ConcreteStrategy（具体策略类）
 */
public class MallardDuck extends Duck {

    //定义MallardDuck的默认行为实现，也可以通过父类的setter方法，动态设置行为
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    void display() {
        System.out.println("I'm a real Mallard duck!");
    }
}
