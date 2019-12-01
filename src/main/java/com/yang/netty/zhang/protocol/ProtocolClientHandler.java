package com.yang.netty.zhang.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtocolClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Person person=MyDataInfo.Person.newBuilder().setName("马云").setAge(20).setAddress("成都").build();
        ctx.channel().writeAndFlush(person);
    }
}
