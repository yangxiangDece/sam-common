package com.yang.designmode.state;

import java.util.Random;

/**
 * 已投币的状态
 * <p>
 * ConcreteState（具体状态）处理来自Context的请求，每一个ConcreteState都提供了它对于请求的实现。所以，当Context改变状态时行为也跟着改变。
 */
public class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    private Random random = new Random(System.currentTimeMillis());

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        //如果是这百分之十中的，而且有足够的糖果的话，我们就进入WinnerState状态，否则，就进入SoldState状态（跟平常一样）
        int winner = random.nextInt(10);
        if (winner == 0 && gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}
