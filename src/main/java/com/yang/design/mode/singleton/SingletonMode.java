package com.yang.design.mode.singleton;

public class SingletonMode {

    private String name;

    private static class Instance {
        private static SingletonMode singletonMode=new SingletonMode();
    }

    public static SingletonMode getInstance(){
        return Instance.singletonMode;
    }
}
