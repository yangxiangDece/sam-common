package com.yang.netty.zhang.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<Integer> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Integer msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("=="+channel.remoteAddress());
        System.out.println("from client:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
