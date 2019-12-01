package com.yang.netty.zhang.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

    public static void main(String[] args) throws Exception {
        new EchoClient("localhost", 8085).start();
    }

    public void start() throws Exception {
        final EchoClientHandler clientHandler = new EchoClientHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 添加一个EchoServerHandler到子Channel的ChannelPipeline中
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // EchoServerHandler 被标注为@Shareable，所以我们可以总是使用相同的实例
                            // 当一个新的连接被接收时，一个新的子Channel将会被创建，而ChannelInitializer将会把EchoServerHandler的实例添加到该Channel
                            // 的ChannelPipeline中，这样，ChannelHandler将会收到有关入站消息的通知。
                            ch.pipeline().addLast(clientHandler);
                        }
                    });
            // 异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            // 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
