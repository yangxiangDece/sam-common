package com.yang.h.loadbalancer.random;

import com.yang.h.loadbalancer.ServiceIps;

import java.util.List;
import java.util.Random;

/**
 * 随机算法
 */
public class RandomS {

    public static void main(String[] args) {
        List<String> ips = ServiceIps.getListIps();

        int size = ips.size();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(size);
            System.out.println(ips.get(r));
        }
    }
}
