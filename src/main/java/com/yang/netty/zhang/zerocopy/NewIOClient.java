package com.yang.netty.zhang.zerocopy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(9999));
        socketChannel.configureBlocking(false);

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put("大家好，我是渣渣辉！".getBytes("UTF-8"));

        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }
}
