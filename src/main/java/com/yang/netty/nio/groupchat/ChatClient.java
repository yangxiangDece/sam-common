package com.yang.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ChatClient {

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();

        // 接收数据
        Thread thread = new Thread(() -> {
           while (!Thread.currentThread().isInterrupted()) {
               chatClient.read();
               try {
                   TimeUnit.SECONDS.sleep(3);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        thread.start();

        // 发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            chatClient.send(msg);
        }
    }

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 8089;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public ChatClient() {
        // 初始化
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(username + " is ok...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        msg = username + " 说：" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            int readChannels = selector.select();
            if (readChannels > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        // 准备读
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int read = channel.read(byteBuffer);
                        String msg = new String(byteBuffer.array(), 0, read);
                        System.out.println(msg);
                    }
                    iterator.remove();
                }
            } else {
                System.out.println("没有可用的通道...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
