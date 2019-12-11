package com.yang.designpattern.han.bridge;

public class Vivo implements Brand {

    @Override
    public void open() {
        System.out.println("vivo - 开机");
    }

    @Override
    public void close() {
        System.out.println("vivo - 关机");
    }

    @Override
    public void call() {
        System.out.println("vivo - 打电话");
    }
}
