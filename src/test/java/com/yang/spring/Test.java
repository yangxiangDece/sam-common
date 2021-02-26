package com.yang.spring;

import com.yang.common.ThreadPoolExecutorFactory;

import java.util.concurrent.ExecutorService;

public class Test {

    public static void main(String[] args) {
        ExecutorService executorService = ThreadPoolExecutorFactory.getThreadPoolExecutor();
        int threadSize = 10;
        for (int i = 1; i <= threadSize; i++) {
            int finalI = i;
            executorService.execute(() -> updateMergerName(finalI, threadSize));
        }
    }

    private static void updateMergerName(int thread, int threadSize) {
        System.out.println(thread);
    }
}
