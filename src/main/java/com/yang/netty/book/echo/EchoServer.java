package com.yang.netty.book.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        new EchoServer(8085).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 添加一个EchoServerHandler到子Channel的ChannelPipeline中
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // EchoServerHandler 被标注为@Shareable，所以我们可以总是使用相同的实例
                            // 当一个新的连接被接收时，一个新的子Channel将会被创建，而ChannelInitializer将会把EchoServerHandler的实例添加到该Channel
                            // 的ChannelPipeline中，这样，ChannelHandler将会收到有关入站消息的通知。
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = serverBootstrap.bind().sync();
            // 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }
}
