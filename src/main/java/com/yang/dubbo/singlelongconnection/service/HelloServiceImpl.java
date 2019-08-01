package com.yang.dubbo.singlelongconnection.service;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
