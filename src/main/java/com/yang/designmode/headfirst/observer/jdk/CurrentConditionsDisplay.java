package com.yang.designmode.headfirst.observer.jdk;

import com.yang.designmode.headfirst.observer.myself.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * 实现jdk中的Observer接口以及自定义的DisplayElement接口
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("jdk current conditions display,temperature" + temperature + ",humidity:" + humidity);
    }

    //采用拉取的方式，观察者只需要拉取自己需要的信息，而不是让可观察者返回所有的信息
    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
