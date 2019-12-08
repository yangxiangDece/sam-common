package com.yang.designmode.headfirst.state;

/**
 * Context（上下文）是一个类，它可以拥有一些内部状态，在我们的例子中，GumballMachine就是这个Context
 */
public class GumballMachine {

    //糖果已经售完状态
    private State soldOutState;
    //没有投币的状态
    private State noQuarterState;
    //已投币的状态
    private State hasQuarterState;
    //刚买了糖果的状态
    private State soldState;

    //赢家，每个人有百分之十的机会得到两颗糖
    private State winnerState;

    private State state = null;
    private int count;

    public GumballMachine(int numberGumballs) {
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.soldState = new SoldState(this);
        this.soldOutState = new SoldOutState(this);
        this.winnerState = new WinnerState(this);
        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    //不需要再GumballMachine中准备一个dispense()的动作方法，因为这只是一个内部的动作，用户不可以直接要求机器发放糖果。
    //但是我们是在状态对象的turnCrank()方法中调用dispense()方法的
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    //这个方法允许其他的对象将机器的状态转到不同的状态
    public void setState(State state) {
        this.state = state;
    }

    //释放糖果的方法
    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count--;
        }
    }

    //当糖果售完时，重新填充糖果，这个方法提供这个功能，并且更新状态为没有投币的状态
    public void refill(int count) {
        this.count = count;
        state = noQuarterState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }
}
