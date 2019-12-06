package com.yang.h.loadbalancer.random;

import com.yang.h.loadbalancer.ServiceIps;

import java.util.Map;
import java.util.Random;

/**
 * 加权随机算法
 * 过权值大小设定范围
 */
public class WightRandomRange {

    public static void main(String[] args) {
        Map<String, Integer> mapIps = ServiceIps.getMapIps();

        // 计算总权重值
        int totalWight = mapIps.values().stream().mapToInt(i -> i).sum();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(totalWight);
            System.out.print("r=" + r + "    ");
            for (Map.Entry<String, Integer> entry : mapIps.entrySet()) {
                // 5 2 1
                if (r <= entry.getValue()) {
                    System.out.println(r + "：" + entry.getKey());
                    break;
                } else {
                    r = r - entry.getValue();
                }
            }
        }
    }
}
