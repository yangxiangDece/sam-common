package com.yang.dubbo.config.test;

import com.yang.dubbo.config.DubboProviderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class DubboProviderConfigTest {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(DubboProviderConfig.class);
        System.out.println("DubboProviderConfigTest...容器启动完成!");
        System.in.read();
    }
}
