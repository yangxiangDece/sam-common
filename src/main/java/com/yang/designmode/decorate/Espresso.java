package com.yang.designmode.decorate;

/**
 * ConcreteComponent（具体构件）
 * <p>
 * 饮料类：浓缩咖啡
 * Espresso扩展自Beverage类，因为Espresso是一种饮料
 */
public class Espresso extends Beverage {

    //设置饮料的描述，每种饮料都不一样，都有各自的描述
    public Espresso() {
        description = "Espresso Coffee";
    }

    //需要计算Espresso的价格，现在不需要管调料的价格，直接把Espresso的价格返回即可
    @Override
    public double cost() {
        return 1.99;
    }
}
