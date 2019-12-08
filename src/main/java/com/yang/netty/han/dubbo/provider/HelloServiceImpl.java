package com.yang.netty.han.dubbo.provider;

import com.yang.netty.han.dubbo.interfaces.HelloService;

import java.util.UUID;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String msg) {
        System.out.println("方法被调用：" + msg);
        return UUID.randomUUID().toString() + "===" + msg;
    }
}
