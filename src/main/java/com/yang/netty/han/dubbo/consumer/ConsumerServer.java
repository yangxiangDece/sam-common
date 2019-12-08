package com.yang.netty.han.dubbo.consumer;

import com.yang.netty.han.dubbo.ProtocolConstants;
import com.yang.netty.han.dubbo.interfaces.HelloService;
import com.yang.netty.han.dubbo.netty.NettyClient;

public class ConsumerServer {

    public static void main(String[] args) {
        NettyClient consumer = new NettyClient("127.0.0.1", 9999);
        HelloService helloService = (HelloService) consumer.getBean(HelloService.class, ProtocolConstants.protocol_name);
        System.out.println(helloService);
        String hello = helloService.hello("hello double!!");
        System.out.println("收到消息：" + hello);
    }
}
