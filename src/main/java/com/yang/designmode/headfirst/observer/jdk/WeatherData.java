package com.yang.designmode.headfirst.observer.jdk;

import java.util.Observable;

/**
 * 不再需要最终观察者，也不需要管理注册与删除，这些都交给父类处理，本类只需要处理自己的业务逻辑即可
 * <p>
 * Observable对观察者Observer支持两种方式获取数据：拉、推
 * <p>
 * java.util.Observable中不够灵活，他是一个类，不方便扩展，可以自己实现一套观察者模式，比如com.yang.designmode.observer.myself包下
 * <p>
 * Swing大量使用观察者模式，许多GUI框架也是如此
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
    }

    /**
     * 注意：这里没有调用notifyObservers()传送数据对象，这表示我们采用的做法是“拉”
     */
    public void measurementsChanged() {
        setChanged();
        notifyObservers();
        //如果想要采取推的方式，在这里组装数据，通过参数param传递给Observable，Observable会循环通知每一个观察者并传递这个数据
//        notifyObservers(param);
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    /**
     * 下面这些getter方法，是因为我们采用的是“拉”的做法，观察者会利用这些方法获取WeatherData对象的状态
     */
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
