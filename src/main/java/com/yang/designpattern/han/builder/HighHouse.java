package com.yang.designpattern.han.builder;

public class HighHouse extends AbstractHouseBuilder {

    @Override
    protected void buildBasic() {
        System.out.println("高层 - 打地基");
    }

    @Override
    protected void buildWalls() {
        System.out.println("高层 - 打地基");
    }

    @Override
    protected void roofed() {
        System.out.println("高层 - 打地基");
    }
}
