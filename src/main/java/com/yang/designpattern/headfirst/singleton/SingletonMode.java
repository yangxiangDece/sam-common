package com.yang.designpattern.headfirst.singleton;

public class SingletonMode {

    private static class Instance {
        private static SingletonMode singletonMode = new SingletonMode();
    }

    public static SingletonMode getInstance() {
        return Instance.singletonMode;
    }
}
