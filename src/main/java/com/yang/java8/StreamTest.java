package com.yang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {

    private static final List<String> list = Arrays.asList("hello", "word", "linked");

    public static void main(String[] args) {

        List<String> collect = list.parallelStream().map(word -> word.split(""))
                .flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect);

        int n = 10000;
        // 自动装箱与拆箱 严重影响性能

        long start = System.currentTimeMillis();
        Long reduce = Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
        System.out.println(reduce);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        long reduce1 = LongStream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
        System.out.println(reduce1);
        System.out.println("耗时：" + (System.currentTimeMillis() - start1));

        long start3 = System.currentTimeMillis();
        long reduce2 = LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
        System.out.println(reduce2);
        System.out.println("耗时：" + (System.currentTimeMillis() - start3));

    }
}
