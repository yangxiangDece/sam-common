package com.yang.spring;

public class Test {

    public static void main(String[] args) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 129; i++) {
            builder.append("map.put(").append(i).append(",").append("\"").append("\"").append(");").append("\n");
        }
        System.out.println(builder.toString());
    }
}