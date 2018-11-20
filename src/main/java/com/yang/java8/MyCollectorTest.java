package com.yang.java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollectorTest<T> implements Collector<T,Set<T>,Set<T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoke!");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoke!");
        return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoke!");
        return (item1,item2) -> {
            item1.addAll(item2);
            return item1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher invoke!");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoke!");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list=Arrays.asList("hello","world","hello world","welcome","hello");
        Set<String> set=list.stream().collect(new MyCollectorTest<>());
        System.out.println(set);
    }
}
