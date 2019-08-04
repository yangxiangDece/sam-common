package com.yang.netty.video.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();

        pipeline.addLast(new MyByteToIntegerDecoder());
        pipeline.addLast(new MyIntegerToByteEncoder());
        pipeline.addLast(new MyClientHandler());
    }
}
