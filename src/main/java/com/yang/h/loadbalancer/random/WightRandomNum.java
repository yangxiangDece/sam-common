package com.yang.h.loadbalancer.random;

import com.yang.h.loadbalancer.ServiceIps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 加权随机算法
 * 通过权值个数加入集合
 */
public class WightRandomNum {

    public static void main(String[] args) {
        Map<String, Integer> mapIps = ServiceIps.getMapIps();

        List<String> ips = new ArrayList<>();
        mapIps.forEach((key, value) -> {
            for (Integer i = 0; i < value; i++) {
                ips.add(key);
            }
        });

        int size = ips.size();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(size);
            System.out.println(ips.get(r));
        }
    }
}
