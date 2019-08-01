package com.yang.dubbo.singlelongconnection;

import java.lang.reflect.Proxy;

/**
 * RPC代理工厂类
 */
public class RpcProxyFactory {

    public static <T> T getMultService(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                new RpcNIoMultHandler());
    }
}
