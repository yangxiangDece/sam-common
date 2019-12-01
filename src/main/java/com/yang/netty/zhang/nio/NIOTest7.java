package com.yang.netty.zhang.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOTest7 {

    public static void main(String[] args) throws Exception {
        int[] ports=new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector=Selector.open();
        for (int i=0;i<ports.length;i++) {
            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost",ports[i]));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口："+ports[i]);
        }
        while (true) {
            int numbers=selector.select();
            System.out.println("numbers:"+numbers);
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            System.out.println("selectionKeys:"+selectionKeys);
            Iterator<SelectionKey> iterator=selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey=iterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel= (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("获得客户端连接："+socketChannel);
                } else if (selectionKey.isReadable()){
                    SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    StringBuilder builder=new StringBuilder();
                    int read=0;
                    while ((read=socketChannel.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        builder.append(new String(byteBuffer.array(),0,read));
                        byteBuffer.clear();
                    }
                    System.out.println("接收到的内容："+builder.toString());
                }
                iterator.remove();
            }
        }
    }
}
