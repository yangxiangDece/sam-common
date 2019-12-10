package com.yang.designpattern.headfirst.singleton;

public enum  SingletonEnum {
    INSTANCE;
    public void say() {
        System.out.println("我是枚举单例模式...");
    }
}
