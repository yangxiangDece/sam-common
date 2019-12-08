package com.yang.designmode.headfirst.state;

/**
 * 糖果售完的状态
 * <p>
 * ConcreteState（具体状态）处理来自Context的请求，每一个ConcreteState都提供了它对于请求的实现。所以，当Context改变状态时行为也跟着改变。
 */
public class SoldOutState implements State {
    
    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You Can't insert a quarter, the machine is sold out");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You Cant't eject, you haven't inserted a quarter yet");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned, but there's no gumballs");
    }

    @Override
    public void dispense() {
        System.out.println("No gumballs dispensed");
    }
}
