package com.yang.design.mode.state;

/**
 * State接口定义了一个所有具体状态的共同接口，任何状态都实现这个相同的接口，这样一来，状态之间可以互相替换。
 */
public interface State {
    /**
     * 投币
     */
    void insertQuarter();

    /**
     * 退币
     */
    void ejectQuarter();

    /**
     * 转动转盘
     */
    void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();
}
