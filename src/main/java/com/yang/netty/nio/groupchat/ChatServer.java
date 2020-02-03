package com.yang.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class ChatServer {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 8089;

    public ChatServer() {
        // 初始化
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int count = selector.select(2000);
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                            System.out.println(socketChannel.getRemoteAddress() + "上线了...");
                        } else if (key.isReadable()) {
                            readData(key);
                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("监听中...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey key) {
        System.out.println("读取数据...");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        try {
            int read = socketChannel.read(byteBuffer);
            byteBuffer.flip();
            String msg = new String(byteBuffer.array(), 0, read);
            System.out.println(socketChannel.getRemoteAddress() + "：" + msg);
            // 向其他客户端转发消息
            sendInfoToOtherClients(msg, socketChannel);
        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + " 离线了...");
                // 取消注册
                key.cancel();
                // 关闭通道
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel self) {
        System.out.println("服务器消息转发中...");
        try {
            for (SelectionKey selectionKey : selector.keys()) {
                Channel targetChannel = selectionKey.channel();
                // 排除自己
                if (targetChannel instanceof SocketChannel && targetChannel != self) {
                    SocketChannel dest = (SocketChannel) targetChannel;
                    ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                    dest.write(byteBuffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
