package com.yang.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringQueueSenderTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-content-activemq.xml");
        SpringQueueSender springQueueSender = applicationContext.getBean(SpringQueueSender.class);
        springQueueSender.sendMessage("哈哈哈哈了第三方就阿里山的风景哦调试");
    }
}
