package com.yang.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboProvider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("dubbo/spring-dubbo-provider.xml");
        applicationContext.start();
        System.out.println("DubboProvider...容器启动成功!");
        System.in.read();
    }
}
