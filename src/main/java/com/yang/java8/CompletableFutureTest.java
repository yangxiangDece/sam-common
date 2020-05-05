package com.yang.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CompletableFutureTest {

    public static void main(String[] args) {
        IntStream.range(0, 10).boxed()
                .forEach(i ->
                        CompletableFuture.supplyAsync(() -> action1(i))
                                .thenAccept(CompletableFutureTest::action2)
                                .whenComplete((v, t) -> complete())
                );
    }

    private static Random random = new Random();

    private static int action1(int param) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("action 1 finish , param: " + param);
        return random.nextInt(10);
    }

    private static void action2(Integer value) {
        System.out.println("action 2 doing value: " + value);
    }

    private static void complete() {
        System.out.println("complete  doing");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
