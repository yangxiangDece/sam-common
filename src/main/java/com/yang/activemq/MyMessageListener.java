package com.yang.activemq;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("=== MQ:");
        System.out.println(message);
    }
}
