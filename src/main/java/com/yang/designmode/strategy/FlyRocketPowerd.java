package com.yang.designmode.strategy;

/**
 * 这是飞行行为的实现，给用火箭飞的鸭子使用
 */
public class FlyRocketPowerd implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("我用火箭飞...");
    }
}
