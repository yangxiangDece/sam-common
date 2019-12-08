package com.yang.designmode.headfirst.decorate;

/**
 * Component（抽象构件）
 */
public abstract class Beverage {

    //每种饮料都不一样，都有各自的描述
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    //价格
    public abstract double cost();
}
