package com.yang.java8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    private static final List<String> list= Arrays.asList("hello","word","linked");
    public static void main(String[] args) {

        list.stream().mapToInt(item -> {
            System.out.println(item);
            return item.length();
        }).filter(item -> item==5).findFirst().ifPresent(System.out::println);

        list.stream().map(item->item.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

    }

    private void test1(){
        Set<String> set=list.stream().collect(HashSet::new,HashSet::add,HashSet::addAll);
        Set<String> set1 = list.stream().collect(Collectors.toSet());
        list.stream().collect(Collectors.counting());

    }
}
