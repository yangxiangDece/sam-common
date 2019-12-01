package com.yang.dubbo.singlelongconnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 客户端代码
 */
public class RpcNioMultClient {

    // 通道管理器
    private Selector selector;
    // 通道
    private SocketChannel channel;
    private String serverIp = "localhost";
    private int port = 8080;

    private RpcNioMultClient() {
        // 初始化client
        initClient();
        new Thread(this::listen).start();
    }

    private static class RpcNioMultClientHolder {
        private final static RpcNioMultClient rpcNioMultClient = new RpcNioMultClient();
    }

    public static RpcNioMultClient getInstance() {
        return RpcNioMultClientHolder.rpcNioMultClient;
    }

    public void initClient() {
        try {
            // 打开一个通道
            channel = SocketChannel.open();
            // 设置为非阻塞
            channel.configureBlocking(false);
            // 获得通道管理器，用于监听通道事件
            selector = Selector.open();
            // 建立连接
            channel.connect(new InetSocketAddress(serverIp, port));
            // 由于是非阻塞，所以可能连接并未建立完成，调用finishConnect完成连接
            if (channel.isConnectionPending()) {
                channel.finishConnect();
            }
            System.out.println("客户端初始化完成，建立连接完成...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                // 绑定到通道管理器，监听可读事件，因为客户端只需要从服务端获得数据然后读取，所以只需要监听READ事件
                channel.register(selector, SelectionKey.OP_READ);
                // 开始轮询read事件
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 删除已选的key，防止重复处理
                    iterator.remove();
                    if (key.isReadable()) {
                        // 读取从服务端发过来的信息
                        readMsgFromServer();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMsgFromServer() {
        ByteBuffer byteBuffer;
        try {
            // 首先读取请求id
            byteBuffer = ByteBuffer.allocate(8);
            int readIdCount = channel.read(byteBuffer);
            if (readIdCount < 0) {
                return;
            }
            byteBuffer.flip();
            Long requestId = byteBuffer.getLong();
            // 读取返回值长度
            byteBuffer = ByteBuffer.allocate(4);
            int readHeadCount = channel.read(byteBuffer);
            if (readHeadCount < 0) {
                return;
            }
            byteBuffer.flip();
            // 消息体长度
            int length = byteBuffer.getInt();
            // 读取消息体
            byteBuffer = ByteBuffer.allocate(length);
            int readBodyCount = channel.read(byteBuffer);
            if (readBodyCount < 0) {
                return;
            }
            byte[] bytes = byteBuffer.array();

            // 将返回值放入指定容器 并且会通知客户端挂起的线程
            RpcContainer.addResponse(requestId, bytes);
        } catch (IOException e) {
            System.out.println("读取数据失败...");
            e.printStackTrace();
        }
    }

    public boolean sendMsgToServer(byte[] bytes) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length + 4);
            // 放入消息长度，然后放入消息体
            byteBuffer.putInt(bytes.length);
            byteBuffer.put(bytes);
            // 写完之后的buffer切换为可读状态
            byteBuffer.flip();
            // 写出消息
            channel.write(byteBuffer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
