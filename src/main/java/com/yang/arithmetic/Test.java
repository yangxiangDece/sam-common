package com.yang.arithmetic;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) {
        TreeMap<Long, String> treeMap = new TreeMap<>();
        treeMap.put(6L,"6");
        treeMap.put(7L,"7");
        treeMap.put(1L,"1");
        treeMap.put(2L,"2");
        treeMap.put(3L,"3");
        treeMap.put(4L,"4");
        treeMap.put(5L,"5");

        SortedMap<Long, String> tailMap = treeMap.tailMap(2L);
        Set<Map.Entry<Long, String>> entries = tailMap.entrySet();
        for (Map.Entry<Long, String> entry : entries) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }
}
