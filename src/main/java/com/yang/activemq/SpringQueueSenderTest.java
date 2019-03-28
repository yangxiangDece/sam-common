package com.yang.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringQueueSenderTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-content-activemq.xml");
        System.out.println("容器启动完成...");
        SpringQueueSender springQueueSender = applicationContext.getBean(SpringQueueSender.class);
        springQueueSender.sendMessage("哈哈哈哈了第三方就阿里山的风景哦调试");
        System.out.println("消息发送完成...");
    }
}
