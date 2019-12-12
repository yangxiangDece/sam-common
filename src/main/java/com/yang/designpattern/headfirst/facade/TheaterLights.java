package com.yang.designpattern.headfirst.facade;

public class TheaterLights {

    public void dim(int num) {
        System.out.println("TheaterLights dim..." + num);
    }

    public void on() {
        System.out.println("TheaterLights on...");
    }
}
