package com.yang.datastructures;

import java.util.*;

/**
 * 一致性hash算法
 */
public class ConsistentHashing {

    public static void main(String[] args) {
        ConsistentHashing consistentHashing = new ConsistentHashing();

        // 初始情况
        consistentHashing.dumpObjectNodeMap("初始情况", 0, 65536);

        // 删除物理节点
        consistentHashing.removePhysicalNode("192.168.1.103");
        consistentHashing.dumpObjectNodeMap("删除物理节点", 0, 65536);

        // 添加物理节点
        consistentHashing.addPhysicalNode("192.168.1.108");
        consistentHashing.dumpObjectNodeMap("删除物理节点", 0, 65536);
    }

    // 物理节点
    private Set<String> physicalNodes = new TreeSet<>();

    {
        physicalNodes.add("192.168.1.101");
        physicalNodes.add("192.168.1.102");
        physicalNodes.add("192.168.1.103");
        physicalNodes.add("192.168.1.104");
    }

    // 虚拟节点 物理节点到虚拟节点的复制倍数
    // 复制倍数为 1 时的均衡性            结果：各节点负荷很不均衡
    // 复制倍数为 32 时的均衡性           结果：各节点负荷比较均衡
    // 复制倍数为 1M 时的均衡性 即1048576  结果：各节点负荷非常均衡
    private final int VIRTUAL_COPIES = 1;

    // 哈希值 => 物理节点
    private TreeMap<Long, String> virtualNodes = new TreeMap<>();

    // 32位的Fowler-Noll-Vo 哈希算法
    private static Long FNVHash(String key) {
        final int p = 16777619;
        Long hash = 2166136261L;
        for (int idx = 0, num = key.length(); idx < num; ++idx) {
            hash = (hash ^ key.charAt(idx)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    // 根据物理节点，构建虚拟节点映射表
    public ConsistentHashing() {
        for (String nodeIp : physicalNodes) {
            addPhysicalNode(nodeIp);
        }
    }

    // 添加物理节点
    public void addPhysicalNode(String nodeIp) {
        for (int idx = 0; idx < VIRTUAL_COPIES; ++idx) {
            long hash = FNVHash(nodeIp + "#" + idx);
            virtualNodes.put(hash, nodeIp);
        }
    }

    // 删除物理节点
    public void removePhysicalNode(String nodeIp) {
        for (int idx = 0; idx < VIRTUAL_COPIES; ++idx) {
            long hash = FNVHash(nodeIp + "#" + idx);
            virtualNodes.remove(hash);
        }
    }

    // 查找对象的映射节点
    public String getObjectNode(String object) {
        long hash = FNVHash(object);
        // 所有大于hash的节点 tailMap(fromKey)方法是通过key升序以后，取出大于fromKey的所有集合
        SortedMap<Long, String> tailMap = virtualNodes.tailMap(hash);
        // 取找到的hash节点的第一个，这是一致性hash算法的规则
        Long key = tailMap.isEmpty() ? virtualNodes.firstKey() : tailMap.firstKey();
        return virtualNodes.get(key);
    }

    // 统计对象与节点的映射关系
    public void dumpObjectNodeMap(String label, int min, int max) {
        // 统计IP命中率的分布情况
        // ip => COUNT
        Map<String, Integer> objectNodeMap = new TreeMap<>();
        for (int object = min; object <= max; ++object) {
            String nodeIp = getObjectNode(Integer.toString(object));
            Integer count = objectNodeMap.get(nodeIp);
            objectNodeMap.put(nodeIp, (count == null ? 0 : ++count));
        }
        // 打印
        double totalCount = max - min + 1;
        System.out.println("=====================" + label + "======================");
        for (Map.Entry<String, Integer> entry : objectNodeMap.entrySet()) {
            long percent = (int) ((entry.getValue() / totalCount) * 100);
            System.out.println("IP=" + entry.getKey() + ":RATE=" + percent + "%");
        }
    }
}
