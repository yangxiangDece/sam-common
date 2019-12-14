package com.yang.designpattern.han.visitor;

public abstract class Action {

    protected abstract void getManResult(Man man);

    protected abstract void getWomanResult(Woman woman);
}
