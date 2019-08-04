package com.yang.netty.video.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class NIOTest8 {

    private static final Map<String,SocketChannel> CHANNEL_MAP=new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));

        Selector selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                //一直阻塞
                int numbers=selector.select();
                System.out.println("numbers:"+numbers);
                Iterator<SelectionKey> iterator=selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey=iterator.next();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server= (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel=server.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);

                        String key="["+ UUID.randomUUID().toString()+"]";
                        CHANNEL_MAP.put(key,socketChannel);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                        StringBuilder builder=new StringBuilder();
                        int read;
                        while ((read=socketChannel.read(byteBuffer)) !=-1) {
                            byteBuffer.flip();
                            builder.append(new String(byteBuffer.array(),0,read,"UTF-8"));
                            byteBuffer.clear();
                        }
                        String message=builder.toString();

                        //向所有的其他聊天者发送消息，如果是自己则显示发送者是自己，否则显示发送者的key
                        String sendKey=null;
                        for (Map.Entry<String,SocketChannel> entry:CHANNEL_MAP.entrySet()) {
                            if(entry.getValue()==socketChannel){
                                sendKey=entry.getKey();
                            }
                        }
                        for (Map.Entry<String,SocketChannel> entry:CHANNEL_MAP.entrySet()) {
                            if (entry.getKey().equals(sendKey)) {
                                message="我："+message;
                            } else {
                                message=sendKey+"："+message;
                            }
                            byte[] bytes=message.getBytes("UTF-8");
                            SocketChannel channel=entry.getValue();
                            ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
                            writeBuffer.put(bytes);
                            writeBuffer.flip();
                            channel.write(writeBuffer);
                        }
                    }
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                selector.close();
            }
        }
    }
}
