package com.yang.design.mode.observer.myself;

public class MainTest {

    public static void main(String[] args) {
        WeatherData weatherData=new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay=new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay=new StatisticsDisplay(weatherData);
        weatherData.setMeasurements(80,98,56);
    }
}
