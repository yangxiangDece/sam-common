package com.yang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ChannelClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8085));
        ByteBuffer byteBuffer = ByteBuffer.wrap("你好啊，我是客户端9527".getBytes(StandardCharsets.UTF_8));
        socketChannel.write(byteBuffer);
    }
}
