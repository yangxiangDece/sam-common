package com.yang.netty.nio;

import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class ChatServerTest {

    private static final Map<String, SocketChannel> CHANNEL_MAP = new HashMap<>();

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 9527));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端已启动...");
        while (true) {
            //一直阻塞
            int numbers = selector.select();
            System.out.println("numbers:" + numbers);
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    String key = "[" + UUID.randomUUID().toString() + "]";
                    CHANNEL_MAP.put(key, socketChannel);
                    System.out.println("获得客户端连接：" + socketChannel.hashCode());
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    StringBuilder builder = new StringBuilder();
                    int read;
                    while ((read = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        builder.append(new String(byteBuffer.array(), 0, read, "UTF-8"));
                        byteBuffer.flip();
                    }
                    String message = builder.toString();
                    System.out.println("message:" + message);

                    if (StringUtils.isNotBlank(message)) {
                        // 向所有的其他聊天者发送消息，如果是自己则显示发送者是自己，否则显示发送者的key
                        String sendKey = null;
                        for (Map.Entry<String, SocketChannel> entry : CHANNEL_MAP.entrySet()) {
                            if (entry.getValue() == socketChannel) {
                                sendKey = entry.getKey();
                            }
                        }
                        for (Map.Entry<String, SocketChannel> entry : CHANNEL_MAP.entrySet()) {
                            message = entry.getKey().equals(sendKey) ? ("我：" + message) : (sendKey + "：" + message);
                            System.out.println("send message:" + message);
                            ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes(Charset.forName("UTF-8")));
                            writeBuffer.flip();
                            entry.getValue().write(writeBuffer);
                        }
                    }
                }
                iterator.remove();
            }
        }
    }
}
