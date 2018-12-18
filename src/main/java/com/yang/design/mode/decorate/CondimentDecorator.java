package com.yang.design.mode.decorate;

/**
 * Decorator（抽象装饰类）
 * CondimentDecorator(调料)抽象类，也就是抽象装饰者类
 */
public abstract class CondimentDecorator extends Beverage {
    /**
     * 所有的调料装饰者都必须重新实现getDescription()方法
     */
    public abstract String getDescription();
}
