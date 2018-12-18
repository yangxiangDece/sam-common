package com.yang.design.mode.decorate;

public class MainTest {

    public static void main(String[] args) {
        Beverage espresso=new Espresso();
        //无任何装饰
        System.out.println(espresso.getDescription() +" $"+espresso.cost());

        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Whip(darkRoast);
        System.out.println(darkRoast.getDescription() +" $"+darkRoast.cost());

    }
}
