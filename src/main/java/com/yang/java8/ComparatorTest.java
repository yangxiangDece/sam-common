package com.yang.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    public static void main(String[] args) {
        List<String> list= Arrays.asList("comparator","stream","function","accept");
        list.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(list);
    }
}
