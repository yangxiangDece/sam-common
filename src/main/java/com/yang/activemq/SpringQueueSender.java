package com.yang.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringQueueSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String msg) {
        jmsTemplate.send(session -> session.createTextMessage("在Spring中配置消息接收者 ----> Hello activeMQ"));
    }
}
