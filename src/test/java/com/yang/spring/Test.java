package com.yang.spring;

import java.util.Stack;

public class Test {

    public static void main(String[] args) {

        Stack<String> stack=new Stack<>();
        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        System.out.println(stack.pop());
    }
}
