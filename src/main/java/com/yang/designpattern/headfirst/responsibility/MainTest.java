package com.yang.designpattern.headfirst.responsibility;

public class MainTest {

    public static void main(String[] args) {
        Approver director = new Director("张三");
        Approver vicePresident = new VicePresident("李四");
        Approver president = new President("王五");
        Approver congress = new Congress("董事会");

        //创建责任链
        director.setSuccessor(vicePresident);
        vicePresident.setSuccessor(president);
        president.setSuccessor(congress);

        //创建采购单
        PurchaseRequest pr1 = new PurchaseRequest(45000, 10001, "购买图书馆");
        PurchaseRequest pr2 = new PurchaseRequest(90000, 10002, "购买学校");
        PurchaseRequest pr3 = new PurchaseRequest(160000, 10003, "投资房地产");
        PurchaseRequest pr4 = new PurchaseRequest(800000, 10004, "入股阿里");

        director.processRequest(pr1);
        director.processRequest(pr2);
        director.processRequest(pr3);
        director.processRequest(pr4);
    }
}
