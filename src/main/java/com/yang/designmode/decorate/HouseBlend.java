package com.yang.designmode.decorate;

/**
 * ConcreteComponent（具体构件）
 * <p>
 * 饮料类：另一种咖啡
 * HouseBlend扩展自Beverage类，因为HouseBlend是一种饮料
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
