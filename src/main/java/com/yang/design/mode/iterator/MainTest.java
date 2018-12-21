package com.yang.design.mode.iterator;

public class MainTest {

    public static void main(String[] args) {
        Waitress waitress=new Waitress(new PancakeHouseMenu(),new DinerMenu());
        waitress.printMenu();
    }
}
