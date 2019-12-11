package com.yang.designpattern.han.builder;

public abstract class AbstractHouseBuilder {

    protected House house = new House();

    protected abstract void buildBasic();

    protected abstract void buildWalls();

    protected abstract void roofed();

    public House buildHouse() {
        return house;
    }
}
