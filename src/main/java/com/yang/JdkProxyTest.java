package com.yang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) {
        UserService userService = new JDKDynamicProxy(new UserServiceImpl()).getProxy();
        userService.addUser("sam");
    }
}

interface UserService {

    void addUser(String username);
}

class UserServiceImpl implements UserService {

    @Override
    public void addUser(String username) {
        System.out.println("add " + username + " success...");
    }
}

class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before method invoke...");
        Object result = method.invoke(target,args);
        System.out.println("after method invoke...");

        return result;
    }
}