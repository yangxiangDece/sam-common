package com.yang.designmode.decorate;

/**
 * ConcreteDecorator（具体装饰类）
 * 调料类：加奶，加奶是一个装饰者，所以让它扩展自CondimentDecorator
 */
public class Whip extends CondimentDecorator {

    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , whip";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.36;
    }
}
