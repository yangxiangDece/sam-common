package com.yang.java8;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class MyTest {

    public static void main(String[] args) {
        Student student1=new Student("zhangsan",20,100);
        Student student2=new Student("lisi",30,80);
        Student student3=new Student("wangwu",40,90);
        Student student4=new Student("wangwu",33,90);
        List<Student> list=Arrays.asList(student1,student2,student3,student4);

        Map<String, List<Student>> collect = list.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(collect);

        Map<String, Long> collect1 = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        System.out.println(collect1);

        Map<String, Double> collect2 = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));
        System.out.println(collect2);

        Map<Boolean, List<Student>> collect3 = list.stream().collect(Collectors.groupingBy(item -> item.getScore() >= 90));
        System.out.println(collect3);

        list.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        list.stream().min(Comparator.comparingInt(Student::getScore)).ifPresent(System.out::println);
        list.stream().max(Comparator.comparingInt(Student::getScore)).ifPresent(System.out::println);
        list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);

        Double collect4 = list.stream().collect(Collectors.averagingInt(Student::getScore));
        System.out.println(collect4);

        Integer collect55 = list.stream().mapToInt(Student::getScore).sum();
        System.out.println(collect55);
        Integer collect5 = list.stream().collect(Collectors.summingInt(Student::getScore));

        System.out.println(collect5);

        IntSummaryStatistics intSummaryStatistics = list.stream().collect(Collectors.summarizingInt(Student::getScore));
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getSum());

        LongSummaryStatistics collect6 = list.stream().collect(Collectors.summarizingLong(Student::getScore));
        System.out.println(collect6);

        System.out.println(list.stream().map(Student::getName).collect(Collectors.joining()));
        System.out.println(list.stream().map(Student::getName).collect(Collectors.joining(",")));
        System.out.println(list.stream().map(Student::getName).collect(Collectors.joining(",","<begin>","<end>")));

        System.out.println("=======");

        MyTest myTest=new MyTest();

        Consumer<Integer> consumer= System.out::println;
        IntConsumer intConsumer=System.out::println;
        System.out.println(consumer instanceof Consumer);
        System.out.println(intConsumer instanceof IntConsumer);

        myTest.test(consumer);
        myTest.test(consumer::accept);
        myTest.test(intConsumer::accept);
        //也就是说对于当前自定义这个test方法，它既可以传递一个引用，比如consumer，可以传递一个lamdba表达式，比如：intConsumer::accept，其实这是一个lamdba表达式

        
    }

    public void test(Consumer<Integer> consumer){
        consumer.accept(100);
    }

    public static String convert(String a,Function<String,String> function){
        return function.apply(a);
    }

    public static String composeTest(double a,Function<Integer,String> function1,Function<Number,Integer> function2){
        return function1.compose(function2).apply(a);
    }
    public static String composeTest2(Double a,Function<Double,Double> function1,Function<Number,String> function2){
        return function1.andThen(function2).apply(a);
    }

    public static int compute(int a, int b, BiFunction<Integer,Integer,Integer> function){
        return function.apply(a,b);
    }
}
