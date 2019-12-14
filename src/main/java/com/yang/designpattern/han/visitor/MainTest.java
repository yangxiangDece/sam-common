package com.yang.designpattern.han.visitor;

/**
 * 访问者模式
 */
public class MainTest {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();

        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());

        Success success = new Success();
        objectStructure.display(success);

        System.out.println("===================================");

        Fail fail = new Fail();
        objectStructure.display(fail);
    }
}
