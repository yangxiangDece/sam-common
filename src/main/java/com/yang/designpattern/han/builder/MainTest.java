package com.yang.designpattern.han.builder;

public class MainTest {

    public static void main(String[] args) {
//        HouseDirector houseDirector = new HouseDirector(new CommonHouse());
        HouseDirector houseDirector = new HouseDirector(new HighHouse());
        House house = houseDirector.buildHouse();
        System.out.println(house);
    }
}
