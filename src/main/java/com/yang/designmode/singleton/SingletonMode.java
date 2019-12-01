package com.yang.designmode.singleton;

public class SingletonMode {

    private static class Instance {
        private static SingletonMode singletonMode = new SingletonMode();
    }

    public static SingletonMode getInstance() {
        return Instance.singletonMode;
    }
}
