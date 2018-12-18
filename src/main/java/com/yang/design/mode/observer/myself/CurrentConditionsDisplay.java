package com.yang.design.mode.observer.myself;

/**
 * 实现自定义Observer、DisplayElement接口
 */
public class CurrentConditionsDisplay implements Observer,DisplayElement {

    private float temperature;
    private float humidity;
    //如果以后想要取消注册，就可以方便调用weatherData.removeObserver(Observer observer)
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    //display()方法就只是把最近的温度和湿度展示出来
    @Override
    public void display() {
        System.out.println("Current conditions display,temperature:"+temperature+"humidity:"+humidity);
    }

    //当update()被调用时，将温度和湿度保存起来，然后调用display()方法
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
}
