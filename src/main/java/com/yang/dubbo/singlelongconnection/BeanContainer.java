package com.yang.dubbo.singlelongconnection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端服务发布时使用的bean容器
 */
public class BeanContainer {

    private final static Map<Class<?>, Object> container = new ConcurrentHashMap<>();

    public static boolean addBean(Class<?> clazz, Object object) {
        container.put(clazz, object);
        return true;
    }

    public static Object getBean(Class<?> clazz) {
        return container.get(clazz);
    }
}
