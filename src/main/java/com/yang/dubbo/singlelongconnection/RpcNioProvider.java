package com.yang.dubbo.singlelongconnection;

import com.yang.dubbo.singlelongconnection.service.HelloService;
import com.yang.dubbo.singlelongconnection.service.HelloServiceImpl;

import java.io.IOException;

/**
 * 服务端服务发布
 */
public class RpcNioProvider {

    public static void main(String[] args) {
        // 将服务放入到容器中 即注册到注册中心
        HelloService helloService = new HelloServiceImpl();
        BeanContainer.addBean(HelloService.class, helloService);
        // 启动服务提供者
        startMultRpcNioServer();
    }

    private static void startMultRpcNioServer() {
        new Thread(() -> {
            try {
                RpcNioMultServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
