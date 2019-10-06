package com.yang.designmode.appearance;

public class DvdPlayer {

    public void on() {
        System.out.println("DvdPlayer on...");
    }

    public void stop() {
        System.out.println("DvdPlayer stop...");
    }

    public void eject() {
        System.out.println("DvdPlayer eject...");
    }

    public void off() {
        System.out.println("DvdPlayer off...");
    }

    public void play(String movie) {
        System.out.println("DvdPlayer play..." + movie);
    }
}
