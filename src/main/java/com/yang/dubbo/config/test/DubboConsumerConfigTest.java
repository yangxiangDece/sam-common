package com.yang.dubbo.config.test;

import com.yang.dubbo.api.entity.User;
import com.yang.dubbo.api.service.UserService;
import com.yang.dubbo.config.DubboConsumerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class DubboConsumerConfigTest {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(DubboConsumerConfig.class);

        UserService userService = (UserService) applicationContext.getBean("userService");
        User user = userService.findById("12133123");
        System.out.println(user);

        System.in.read();
    }
}
