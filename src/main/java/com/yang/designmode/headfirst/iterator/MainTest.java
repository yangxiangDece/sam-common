package com.yang.designmode.headfirst.iterator;

public class MainTest {

    public static void main(String[] args) {
        Waitress waitress = new Waitress(new PancakeHouseMenu(), new DinerMenu());
        waitress.printMenu();
    }
}
