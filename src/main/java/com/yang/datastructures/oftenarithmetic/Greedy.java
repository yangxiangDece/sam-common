package com.yang.datastructures.oftenarithmetic;

import java.util.*;

/**
 * 贪心算法
 */
public class Greedy {

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        Set<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        Set<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        Set<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");
        Set<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        Map<String, Set<String>> broadCasts = new HashMap<>();
        broadCasts.put("k1", set1);
        broadCasts.put("k2", set2);
        broadCasts.put("k3", set3);
        broadCasts.put("k4", set4);
        broadCasts.put("k5", set5);

        Set<String> allAreas = new HashSet<>();
        allAreas.addAll(set1);
        allAreas.addAll(set2);
        allAreas.addAll(set3);
        allAreas.addAll(set4);
        allAreas.addAll(set5);

        // 存放选择的电视台集合
        List<String> selects = new ArrayList<>();

        // 临时集合 存放遍历过程中的电视台覆盖的地区和当前没有覆盖的地区的交集
        Set<String> tempSet = new HashSet<>();
        // 保存一次遍历中，能够覆盖最大未覆盖的地区对应的电视台的key 如果maxKey不为空，则会加入到selects集合
        String maxKey = null;
        while (allAreas.size() != 0) {
            maxKey = null;
            // 如果allAreas不为0，则表示还没有覆盖到所有的地区
            for (String key : broadCasts.keySet()) {
                tempSet.clear();
                // 当前这个key能够覆盖的地区
                Set<String> areas = broadCasts.get(key);
                tempSet.addAll(areas);
                // 求出tempSet和allAreas集合的交集，并将交集赋值给tempSet
                tempSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多，则需要重置maxKey
                // tempSet.size() > broadCasts.get(maxKey).size() 体现出贪心算法的特点，每次都是选择最优的
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadCasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的电视台覆盖的地区，从allAreas去掉
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果：" + selects);
    }
}
