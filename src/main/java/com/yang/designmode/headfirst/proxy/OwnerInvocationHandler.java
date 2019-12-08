package com.yang.designmode.headfirst.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理：运行时才将类创建出来，代码开始执行时，还没有proxy类，它是根据需要从你传入的接口集创建的
 * 通过代理类的一个静态方法Proxy.isProxyClass(personBean.getClass())判断是否是一个代理类，是则返回true
 */
public class OwnerInvocationHandler implements InvocationHandler {

    private PersonBean person;

    private OwnerInvocationHandler(PersonBean personBean) {
        this.person = personBean;
    }

    public static PersonBean getOwnerProxy(PersonBean person) {
        return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new OwnerInvocationHandler(person));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            String methodName = method.getName();
            if (methodName.startsWith("get") || methodName.startsWith("set")) {
                System.out.println("执行方法：" + methodName + ", 参数：" + Arrays.asList(args) + ", proxy：" + proxy);
                return method.invoke(person, args);
            } else if (methodName.equals("setHotOrNotRating")) {
                return new IllegalAccessException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
