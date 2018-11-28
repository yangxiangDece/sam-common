package com.yang.dubbo;

import com.yang.dubbo.api.entity.Order;
import com.yang.dubbo.api.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboConsumer {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("dubbo/spring-dubbo-consumer.xml");
        OrderService orderService = applicationContext.getBean(OrderService.class);
        Order order = orderService.getOrders("123456");
        System.out.println(order);

        System.in.read();
    }
}
