package com.yang.h.loadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceIps {

    private static List<String> LIST_IPS = new ArrayList<>();
    private static Map<String, Integer> MAP_IPS = new HashMap<>();

    public static List<String> getListIps() {
        LIST_IPS.add("A");
        LIST_IPS.add("B");
        LIST_IPS.add("C");
        LIST_IPS.add("D");
        LIST_IPS.add("E");
        LIST_IPS.add("F");
        return LIST_IPS;
    }

    public static Map<String, Integer> getMapIps() {
        MAP_IPS.put("A", 5);
        MAP_IPS.put("B", 1);
        MAP_IPS.put("C", 1);
        return MAP_IPS;
    }
}
