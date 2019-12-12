package com.yang.designpattern.han.flywight;

import java.util.HashMap;
import java.util.Map;

public class WebSiteFactory {

    private Map<String, ConcreteWebSite> pool = new HashMap<>();

    public WebSite getWebSite(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        return pool.get(type);
    }

    public int getWebSiteCount() {
        return pool.size();
    }
}
