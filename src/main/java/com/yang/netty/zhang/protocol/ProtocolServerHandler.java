package com.yang.netty.zhang.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtocolServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) {
        System.out.println(msg.getName());
        System.out.println(msg.getAddress());
        System.out.println(msg.getAge());
    }
}
