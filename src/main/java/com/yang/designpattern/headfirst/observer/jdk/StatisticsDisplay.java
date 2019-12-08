package com.yang.designpattern.headfirst.observer.jdk;

import com.yang.designpattern.headfirst.observer.myself.DisplayElement;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float pressure;
    private Observable weatherData;

    public StatisticsDisplay(Observable weatherData) {
        this.weatherData = weatherData;
        weatherData.addObserver(this);
    }

    //display()方法就只是把最近的温度和湿度展示出来
    @Override
    public void display() {
        System.out.println("jdk statistics display,temperature:" + temperature + "pressure:" + pressure);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.pressure = weatherData.getPressure();
            display();
        }
    }
}
