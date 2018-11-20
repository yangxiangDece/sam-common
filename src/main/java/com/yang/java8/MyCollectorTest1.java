package com.yang.java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollectorTest1<T> implements Collector<T,List<T>,Map<T,T>> {

    @Override
    public Supplier<List<T>> supplier() {
        System.out.println("supplier invoke!");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        System.out.println("accumulator invoke!");
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        System.out.println("combiner invoke!");
        return (item1,item2) -> {
            item1.addAll(item2);
            return item1;
        };
    }

    @Override
    public Function<List<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoke!");
        return item -> item.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoke!");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED,Characteristics.CONCURRENT));

    }

    public static void main(String[] args) {
        List<String> list=Arrays.asList("java","c","c++","c#","python","dd","cc","ad","sdf");
        Map<String, String> map = list.parallelStream().collect(new MyCollectorTest1<>());
        System.out.println(map);
    }
}
