package com.yang.designpattern.han.visitor;

public class Man extends Person {

    @Override
    protected void accept(Action action) {
        action.getManResult(this);
    }
}
