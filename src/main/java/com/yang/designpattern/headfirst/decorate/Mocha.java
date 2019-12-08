package com.yang.designpattern.headfirst.decorate;

/**
 * ConcreteDecorator（具体装饰类）
 * 调料类：摩卡，摩卡是一个装饰者，所以让它扩展自CondimentDecorator
 */
public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    //我们希望叙述不只是描述饮料（例如："DarkRoast"），而是完整地连调料都描述出来（例如：DarkRoast,Mocha），
    //所以首先利用委托的做法，得到一个叙述，然后在其后面加上附加的叙述（例如：“Mocha”）
    @Override
    public String getDescription() {
        return beverage.getDescription() + " , Mocha";
    }

    //要计算带Mocha饮料的价格，首先利用委托被装饰对象，以计算价格，然后在加上Mocha的价格，得到最后结果
    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }
}
