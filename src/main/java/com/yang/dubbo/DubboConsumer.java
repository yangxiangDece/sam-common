package com.yang.dubbo;

import com.yang.dubbo.api.entity.Order;
import com.yang.dubbo.api.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboConsumer {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");
        OrderService orderService = applicationContext.getBean(OrderService.class);
        Order order = orderService.getOrders("123456");
        System.out.println(order);
    }
}
