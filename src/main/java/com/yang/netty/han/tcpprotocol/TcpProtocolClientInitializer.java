package com.yang.netty.han.tcpprotocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class TcpProtocolClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 加入解码器
        pipeline.addLast(new TcpMessageDecoder());
        // 加入编码器
        pipeline.addLast(new TcpMessageEncoder());
        pipeline.addLast(new TcpProtocolClientHandler());
    }
}
