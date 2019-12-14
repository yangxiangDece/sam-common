package com.yang.designpattern.han.visitor;

public class Fail extends Action {

    @Override
    protected void getManResult(Man man) {
        System.out.println("男人得到评价：失败");
    }

    @Override
    protected void getWomanResult(Woman woman) {
        System.out.println("女人得到评价：失败");
    }
}
