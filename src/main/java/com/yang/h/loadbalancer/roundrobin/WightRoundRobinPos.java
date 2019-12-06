package com.yang.h.loadbalancer.roundrobin;

import com.yang.h.loadbalancer.ServiceIps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 加权轮询算法 位置轮询
 * 权值：A:5 B:1 C:1
 * 服务：A A A A A B C
 */
public class WightRoundRobinPos {

    public static void main(String[] args) {
        Map<String, Integer> mapIps = ServiceIps.getMapIps();

        List<String> ips = new ArrayList<>();
        mapIps.forEach((key, value) -> {
            for (int i = 0; i < value; i++) {
                ips.add(key);
            }
        });
        int position = 0;
        for (int i = 0; i < 15; i++) {
            System.out.println(ips.get(position));
            if (++position == ips.size()) {
                position = 0;
            }
        }
    }
}
