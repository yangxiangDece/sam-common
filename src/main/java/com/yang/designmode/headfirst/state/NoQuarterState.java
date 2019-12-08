package com.yang.designmode.headfirst.state;

/**
 * 没有投币的状态
 * <p>
 * ConcreteState（具体状态）处理来自Context的请求，每一个ConcreteState都提供了它对于请求的实现。所以，当Context改变状态时行为也跟着改变。
 */
public class NoQuarterState implements State {

    private GumballMachine gumballMachine;

    //通过构造器得到糖果机的引用，然后将它记录在实例变量中
    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    //如果有人投入了25分钱，我们就打印出一条消息，说我们接受了25分钱，然后改变机器的状态到HasQuarterState
    @Override
    public void insertQuarter() {
        System.out.println("You inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    //如果没给钱，就不能要求退钱
    @Override
    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");
    }

    //如果没给钱，就不能转动转盘
    @Override
    public void turnCrank() {
        System.out.println("You turned, but there's no quarter");
    }

    //如果没得到钱，我们就不能发放糖果
    @Override
    public void dispense() {
        System.out.println("You need to pay first");
    }
}
