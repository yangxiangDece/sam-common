package com.yang.netty.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.TimeUnit;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup CHANNEL_GROUP=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel send=ctx.channel();
        TimeUnit.SECONDS.sleep(1);
        CHANNEL_GROUP.forEach(channel->{
            if(send==channel){
                channel.writeAndFlush("我："+msg);
            } else {
                channel.writeAndFlush(channel.remoteAddress()+"："+msg);
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();

        CHANNEL_GROUP.writeAndFlush("【系统消息】："+channel.remoteAddress()+"加入了房间！");
        CHANNEL_GROUP.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 已上线！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 已下线！");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();

        CHANNEL_GROUP.writeAndFlush("【系统消息】"+channel.remoteAddress()+"离开了房间！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
