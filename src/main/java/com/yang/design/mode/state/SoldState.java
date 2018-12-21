package com.yang.design.mode.state;

/**
 * 刚买了糖果的状态
 *
 * ConcreteState（具体状态）处理来自Context的请求，每一个ConcreteState都提供了它对于请求的实现。所以，当Context改变状态时行为也跟着改变。
 */
public class SoldState implements State {

    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball");
    }

    @Override
    public void dispense() {
        //当前是soldState状态，也就是说顾客顾客已经付钱了，所以需要机器发放糖果
        gumballMachine.releaseBall();
        if (gumballMachine.getCount()>0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}
