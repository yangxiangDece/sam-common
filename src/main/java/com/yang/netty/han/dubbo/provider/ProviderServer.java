package com.yang.netty.han.dubbo.provider;

import com.yang.netty.han.dubbo.netty.NettyServer;

public class ProviderServer {

    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 9999);
    }
}
