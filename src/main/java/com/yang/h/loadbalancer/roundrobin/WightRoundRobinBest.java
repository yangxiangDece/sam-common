package com.yang.h.loadbalancer.roundrobin;

import com.yang.h.loadbalancer.ServiceIps;

import java.util.List;

/**
 * 加权平滑轮询算法 位置轮询
 * 权值：A:5 B:1 C:1
 * 服务：A A B A C A A
 */
public class WightRoundRobinBest {

    public static void main(String[] args) {
        List<String> ips = ServiceIps.getListIps();
    }
}
