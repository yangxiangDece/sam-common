package com.yang.designpattern.han.builder;

public class CommonHouse extends AbstractHouseBuilder {

    @Override
    protected void buildBasic() {
        System.out.println("普通房子 - 打地基");
    }

    @Override
    protected void buildWalls() {
        System.out.println("普通房子 - 砌墙");
    }

    @Override
    protected void roofed() {
        System.out.println("普通房子 - 封底");
    }
}
