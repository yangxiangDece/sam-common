package com.yang.designmode.headfirst.appearance;

public class Amplifier {

    public void on() {
        System.out.println("Amplifier on...");
    }

    public void off() {
        System.out.println("Amplifier off...");
    }

    public void setDvd(DvdPlayer dvd) {
        System.out.println("Amplifier setDvd..." + dvd);
    }

    public void setSurroundSound() {
        System.out.println("Amplifier setSurroundSound...");
    }

    public void setVolume(int volume) {
        System.out.println("Amplifier setVolume..." + volume);
    }
}
