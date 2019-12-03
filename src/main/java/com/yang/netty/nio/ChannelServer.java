package com.yang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelServer {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8085));
        System.out.println("服务端启动完成...");
        while (!Thread.currentThread().isInterrupted()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("有可读数据...");
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read;
            while ((read = socketChannel.read(byteBuffer)) > 0) {
                // 写模式转换为读模式
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, read));
                // 读模式转换为写模式
                byteBuffer.flip();
            }
        }
    }
}
