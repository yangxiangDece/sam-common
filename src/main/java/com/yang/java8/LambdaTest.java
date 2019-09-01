package com.yang.java8;

public class LambdaTest {

    public static void main(String[] args) {
        A a = new C();
        a.hello();
    }

    interface A {
        default void hello() {
            System.out.println("hello A");
        }
    }

    interface B extends A {
        default void hello() {
            System.out.println("hello B");
        }
    }

    static class C implements A,B {

    }
}
