package com.yang.h.loadbalancer.roundrobin;

import com.yang.h.loadbalancer.ServiceIps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 加权平滑轮询算法 位置轮询
 * 权值：A:5 B:1 C:1
 * 服务：A A B A C A A
 */
public class WightRoundRobinBest {

    public static void main(String[] args) {
        Map<String, Integer> mapIps = ServiceIps.getMapIps();

        // 计算总权重值
        int totalWight = mapIps.entrySet().stream().mapToInt(Map.Entry::getValue).sum();

        // 构建权重
        List<Wight> wights = mapIps.entrySet().stream().map(item -> new Wight(item.getKey(), item.getValue(), 0)).collect(Collectors.toList());

        for (int i = 0; i < 7; i++) {

            // 权值：A:5 B:1 C:1
            // 服务：A A B A C A A
            // totalWight = 7
            //      静态权重    动态权重    最大值     IP     新动态权重 = (最大值 - 权重值) + 静态权重
            // 1    5,1,1      0,0,0       5        A      (5-7),1,1 + 5,1,1 = 3,2,2
            // 2    5,1,1      3,2,2       3        A      (3-7),2,2 + 5,1,1 = 1,3,3
            // 3    5,1,1      1,3,3       3        B      1,(3-7),3 + 5,1,1 = 6,-3,4
            // 4    5,1,1      6,-3,4      6        A      (6-7),-3,4 + 5,1,1 = 4,-2,5
            // 5    5,1,1      4,-2,5      5        C      4,-2,(5-7) + 5,1,1 = 9,-1,-1
            // 6    5,1,1      9,-1,-1     9        A      (9-7),-1,-1 + 5,1,1 = 7,0,0
            // 7    5,1,1      7,0,0       7        A      (7-7),0,0 + 5,1,1 = 5,1,1

            // 8    5,1,1      5,1,1       7        A      (7-7),0,0 + 5,1,1 = 5,1,1  到这里又和第一步一样了
            Wight wight = null;
            for (Wight w : wights) {
                w.setConcurrentWight(w.getWight() + w.getConcurrentWight());
                if (wight == null || wight.getConcurrentWight() < w.getConcurrentWight()) {
                    wight = w;
                }
            }
            wight.setConcurrentWight(wight.getConcurrentWight() - totalWight);
            System.out.println(wight.getIp());
        }
    }

    private static class Wight {
        // ip
        private String ip;
        // 静态权重值
        private Integer wight;
        // 动态权重值
        private Integer concurrentWight;

        public Wight(String ip, Integer wight, Integer concurrentWight) {
            this.ip = ip;
            this.wight = wight;
            this.concurrentWight = concurrentWight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getWight() {
            return wight;
        }

        public void setWight(Integer wight) {
            this.wight = wight;
        }

        public Integer getConcurrentWight() {
            return concurrentWight;
        }

        public void setConcurrentWight(Integer concurrentWight) {
            this.concurrentWight = concurrentWight;
        }
    }
}
