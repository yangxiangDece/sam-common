package com.yang.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

public class ChatServer {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 8089;

    public ChatServer() {
        // 初始化
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int count = selector.select(1000);
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        } else if (key.isReadable()) {
                            // 准备读
                            readData(key);
                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("没有连接，继续监听...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        try {
            int read;
            while ((read = socketChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                String msg = new String(byteBuffer.array(), 0, read);
                System.out.println("form 客户端：" + msg);

                // 向其他客户端转发消息
                sendInfoToOtherClients(msg, socketChannel);
                byteBuffer.flip();
            }
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
                    ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(Charset.forName("UTF-8")));
                    dest.write(byteBuffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
