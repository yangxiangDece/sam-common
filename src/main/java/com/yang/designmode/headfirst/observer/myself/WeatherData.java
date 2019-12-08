package com.yang.designmode.headfirst.observer.myself;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteSubject（具体目标）
 * 主题的具体实现
 */
public class WeatherData implements Subject {

    /**
     * 记录观察者
     */
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    /**
     * 将更新的状态告诉每一个观察者，由于观察者都实现同一个Observer接口，所以直接调用update()方法即可
     */
    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(temperature, humidity, pressure));
    }

    /**
     * 当气象站得到更新观测值时，就通知观察者
     */
    public void measurementsChanged() {
        notifyObservers();
    }

    /**
     * 设置气象站观测到的值，并调用通知气象数据发生改变的方法
     *
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
