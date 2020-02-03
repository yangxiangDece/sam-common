package com.yang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * NIO一个重要的特点是：socket主要的读、写、注册和接收函数，在等待就绪阶段都是非阻塞的，真正的I/O操作是同步阻塞的（消耗CPU但性能非常高）。
 */
public class BasicNIOTest {

    public static void main(String[] args) throws Exception {
        Thread serverThread = new Thread(() -> {
            try {
                server();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread clientThread = new Thread(() -> {
            try {
                client();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        serverThread.start();
        TimeUnit.SECONDS.sleep(1);
        clientThread.start();
        clientThread.join();
    }

    private static void server() throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8086));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端已启动...");
        while (!Thread.currentThread().isInterrupted()) {
            int numbers = selector.select();
            System.out.println("numbers:" + numbers);
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("获得客户端连接：" + socketChannel);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    int read = socketChannel.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("接收到的内容：" + new String(byteBuffer.array(), 0, read));
                    }
                }
                iterator.remove();
            }
        }
    }

    private static void client() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8086);
        boolean connect = socketChannel.connect(inetSocketAddress);
        if (!connect) {
            while (!socketChannel.finishConnect()) {
                System.out.println("未连接到服务器，客户端可以做其他事情...");
            }
        }
        // 客户端已完成连接
        String content = "你好啊，我是客户端003";
        ByteBuffer byteBuffer = ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8));
        socketChannel.write(byteBuffer);
    }
}
