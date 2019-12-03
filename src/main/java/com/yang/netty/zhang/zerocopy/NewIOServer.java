package com.yang.netty.zhang.zerocopy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        int read;
        StringBuilder builder = new StringBuilder();
        while ((read = socketChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            builder.append(new String(byteBuffer.array(), 0, read, "UTF-8"));
            byteBuffer.clear();
        }
        System.out.println("content:" + builder.toString());
        socketChannel.close();
        serverSocketChannel.close();
    }
}
