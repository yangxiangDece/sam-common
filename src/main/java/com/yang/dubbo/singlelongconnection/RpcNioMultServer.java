package com.yang.dubbo.singlelongconnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务端，用于与客户端建立连接、读取数据
 */
public class RpcNioMultServer {

    // 通道管理器
    private Selector selector;

    public static void start() throws IOException {
        RpcNioMultServer server = new RpcNioMultServer();
        server.initServer(8080);
        server.listen();
    }

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
     *
     * @param port
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 获取一个通道管理器
        this.selector = Selector.open();
        // 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当该事件到达时，selector.select() 会返回，如果该事件没到达，selector.select() 会一直阻塞。
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() {
        System.out.println("服务端启动成功...");
        while (true) {
            try {
                // 当注册事件到达时，方法返回；否则，该方法一直阻塞
                this.selector.select();
                // 获得selector中选中的迭代器，选中的项为注册事件
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 删除已选择的key
                    iterator.remove();
                    if (key.isAcceptable()) {
                        // 开始处理 连接事件
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        // 获得和客户端连接的通道
                        SocketChannel channel = server.accept();
                        // 设置为非阻塞模式
                        channel.configureBlocking(false);
                        // 在和客户端连接成功之后，为了可以接受到客户端的信息，需要给通道注册读的实际
                        channel.register(this.selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        // 开始处理 读事件
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 读取客户端发过来的信息
                        byte[] bytes = readMsgFromClient(channel);
                        if (bytes != null && bytes.length > 0) {
                            // 读取之后将任务放入线程池异步返回
                            RpcNioThreadPoolTask.addTask(new RpcNioMultServerTask(bytes, channel));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] readMsgFromClient(SocketChannel channel) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            // 首先读取消息头，自己设计的协议头
            int headCount = channel.read(byteBuffer);
            if (headCount < 0) {
                return null;
            }
            byteBuffer.flip();
            // 消息体的长度
            int length = byteBuffer.getInt();
            // 读取消息体
            byteBuffer = ByteBuffer.allocate(length);
            int bodyCount = channel.read(byteBuffer);
            if (bodyCount < 0) {
                return null;
            }
            return byteBuffer.array();
        } catch (IOException e) {
            System.err.println("读取数据异常...");
            e.printStackTrace();
            return null;
        }
    }
}
