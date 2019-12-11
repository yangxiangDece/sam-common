package com.yang.designpattern.han.bridge;

public class XiaoMi implements Brand {

    @Override
    public void open() {
        System.out.println("小米 - 开机");
    }

    @Override
    public void close() {
        System.out.println("小米 - 关机");
    }

    @Override
    public void call() {
        System.out.println("小米 - 打电话");
    }
}
