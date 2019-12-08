package com.yang.designmode.headfirst.observer.myself;

/**
 * Observer（观察者）
 */
public interface Observer {
    /**
     * 所有观察者都必须实现update()方法，以实现观察者接口
     * 当气象观测值改变时，主题会把这些状态值当作方法的参数，传值给观察者
     *
     * @param temp
     * @param humidity
     * @param pressure
     */
    void update(float temp, float humidity, float pressure);
}
