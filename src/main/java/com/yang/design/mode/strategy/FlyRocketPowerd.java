package com.yang.design.mode.strategy;

public class FlyRocketPowerd implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("我用火箭飞...");
    }
}
