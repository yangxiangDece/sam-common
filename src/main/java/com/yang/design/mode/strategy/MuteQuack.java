package com.yang.design.mode.strategy;

/**
 * 这是叫的实现，给不会叫的鸭子使用
 */
public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("我不会叫...");
    }
}
