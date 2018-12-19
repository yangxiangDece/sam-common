package com.yang.design.mode.command;

/**
 * 这些厂商类被用来控制特定的家电自动化装置，在这里，我们用Light类当做例子
 */
public class Light {

    public void on(){
        System.out.println("电灯打开了...");
    }

    public void off(){
        System.out.println("电灯关闭了...");
    }
}
