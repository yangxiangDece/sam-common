package com.yang.netty.han.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class GroupChatClient {

    public static void main(String[] args) throws Exception {
        new GroupChatClient(8086).start();
    }

    private int port;

    public GroupChatClient(int port) {
        this.port = port;
    }

    private void start() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    // 加入解码器 入栈处理器
                    pipeline.addLast("decoder", new StringDecoder());
                    // 加入编码器 出栈处理器
                    pipeline.addLast("encoder", new StringEncoder());
                    pipeline.addLast(new GroupChatClientHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(port)).sync();
            Channel channel = channelFuture.channel();
            System.out.println("=======================" + channel.remoteAddress() + "============================");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg + "\r\n");
            }
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
