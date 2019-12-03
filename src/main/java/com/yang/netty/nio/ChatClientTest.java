package com.yang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ChatClientTest {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 9527);
        boolean connect = socketChannel.connect(inetSocketAddress);
        if (!connect) {
            while (!socketChannel.finishConnect()) {
                System.out.println("未连接到服务器，客户端可以做其他事情...");
            }
        }
        // 客户端已完成连接
        String content = "你好啊，我是客户端003";
        ByteBuffer byteBuffer = ByteBuffer.wrap(content.getBytes(Charset.forName("UTF-8")));
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}
