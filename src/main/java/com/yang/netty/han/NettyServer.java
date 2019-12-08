package com.yang.netty.han;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class NettyServer {

    private static EventExecutorGroup eventExecutors = new DefaultEventExecutorGroup(8);

    public static void main(String[] args) throws Exception {

        // bossGroup 处理客户端连接
        // workGroup 处理网络读写操作

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // ChannelOption.SO_BACKLOG 对应TCP/IP协议listen函数中的backlog参数，用来初始化服务器可选连接队列大小。
                    // 服务端处理客户端请求是串行处理的，所以同一时间只能处理一个客户端连接，
                    // 多个客户端来的时候，服务端将不能处理的客户端请求放在队列中等待处理，backlog参数指定了队列的大小。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
//                            pipeline.addLast(new NettyServerHandlerTaskQueue());
                            pipeline.addLast(eventExecutors, new NettyServerHandlerTaskQueue());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8085).sync();
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    System.out.println("监听端口8085成功...");
                }
            });
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
