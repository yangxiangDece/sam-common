package com.yang.designmode.appearance;

public class MainTest {

    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();
        TheaterLights lights = new TheaterLights();
        Screen screen = new Screen();
        PopcornPopper popper = new PopcornPopper();
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(amp, dvd, projector, lights, screen, popper);
        homeTheaterFacade.watchMovie("Raiders of the Lost Ark");
        homeTheaterFacade.endMovie();
    }
}
