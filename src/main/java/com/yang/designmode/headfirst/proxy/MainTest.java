package com.yang.designmode.headfirst.proxy;

import java.lang.reflect.Proxy;

public class MainTest {

    public static void main(String[] args) {
        PersonBean personBean = OwnerInvocationHandler.getOwnerProxy(new PersonBeanImpl());
        personBean.setName("张三");
        System.out.println(Proxy.isProxyClass(personBean.getClass()));
    }
}
