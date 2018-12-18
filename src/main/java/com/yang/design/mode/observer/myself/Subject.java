package com.yang.design.mode.observer.myself;

/**
 * Subject（目标）
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    /**
     * 当主题状态发生改变时，这个方法会被调用，已通知所有的观察者
     */
    void notifyObservers();
}
