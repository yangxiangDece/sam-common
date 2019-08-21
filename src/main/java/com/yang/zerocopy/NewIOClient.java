package com.yang.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8888));
        socketChannel.configureBlocking(true);

        FileChannel fileChannel = new FileInputStream("D:/download/jdk-8u201-linux-x64.tar.gz").getChannel();

        long startTime = System.currentTimeMillis();

        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节：" + transferCount);
        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
    }
}
