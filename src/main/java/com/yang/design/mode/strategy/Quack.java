package com.yang.design.mode.strategy;

/**
 * 这是叫的实现，给呱呱叫的鸭子使用
 */
public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("呱呱叫...");
    }
}
