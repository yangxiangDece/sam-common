package com.yang.design.mode.decorate;

/**
 * ConcreteComponent（具体构件）
 *
 * 饮料类：DarkRoast咖啡
 * DarkRoast扩展自Beverage类，因为DarkRoast是一种饮料
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return 0.24;
    }
}
