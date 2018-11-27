package com.yang.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboProvider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-dubbo-provider.xml");
        applicationContext.start();

        System.in.read();
    }
}
