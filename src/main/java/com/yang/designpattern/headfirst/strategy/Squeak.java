package com.yang.designpattern.headfirst.strategy;

/**
 * 这是叫的实现，给吱吱叫的鸭子使用
 */
public class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("吱吱叫...");
    }
}
