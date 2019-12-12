package com.yang.designpattern.han.composite;

/**
 * 组合模式
 */
public class MainTest {

    public static void main(String[] args) {
        University university = new University("清华大学", "世界级大学");
        College computerCollege = new College("计算机学院", "计算机学院描述");
        College infoCollege = new College("信息工程学院", "信息工程学院描述");
        computerCollege.add(new Department("软件工程系", "软件工程系简述"));
        computerCollege.add(new Department("计算机科学与技术系", "计算机科学与技术系简述"));
        computerCollege.add(new Department("网络工程系", "网络工程系简述"));

        infoCollege.add(new Department("通信工程", "通信工程简述"));
        infoCollege.add(new Department("信息工程", "信息工程简述"));

//        university.add(new Department("医学院","医学院描述"));

        university.add(computerCollege);
        university.add(infoCollege);

        university.print();
//        computerCollege.print();
//        infoCollege.print();
    }
}
