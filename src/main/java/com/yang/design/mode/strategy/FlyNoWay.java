package com.yang.design.mode.strategy;

/**
 * 这是飞行行为的实现，给不会飞的鸭子使用
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("我不会飞...");
    }
}
