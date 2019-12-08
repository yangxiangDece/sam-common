package com.yang.designpattern.headfirst.strategy;

/**
 * 飞行行为的实现，给真会飞的鸭子使用
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("我真的会飞...");
    }
}
