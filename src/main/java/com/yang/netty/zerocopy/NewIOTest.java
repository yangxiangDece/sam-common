package com.yang.netty.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOTest {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(9999));
        socketChannel.configureBlocking(false);

        String fileName="D:/test/test.zip";
        FileChannel fileChannel=new FileInputStream(fileName).getChannel();
        long startTime=System.currentTimeMillis();

        //transferTo表示将数据从fileChannel中写入到socketChannel
        //transferFrom表示从socketChannel中读取数据，接收数据时才调用，如下
        //fileChannel.transferFrom(socketChannel,0,fileChannel.size());
        long transferCount=fileChannel.transferTo(0,fileChannel.size(),socketChannel);
    }
}
