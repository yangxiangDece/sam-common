package com.yang.designpattern.han.visitor;

public class Woman extends Person {

    @Override
    protected void accept(Action action) {
        action.getWomanResult(this);
    }
}
