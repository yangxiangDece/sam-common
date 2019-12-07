package com.yang.netty.han.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // channel组 管理所有的channel
    // GlobalEventExecutor.INSTANCE 全局的事件执行器 单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 连接刚建立 即 某某某加入聊天室
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将该客户加入聊天的信息发送给其他在线的客户端 即 某某某加入聊天室
        // channelGroup.writeAndFlush 会将 channelGroup 中的所有channel遍历并发送消息
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 加入聊天室 " + simpleDateFormat.format(new Date()) + "\n");
        channelGroup.add(channel);
    }

    // 断开连接 即 某某某离开聊天室
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将该客户离开聊天的信息发送给其他在线的客户端 即 某某某离开聊天室
        // channelGroup.writeAndFlush 会将 channelGroup 中的所有channel遍历并发送消息
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开聊天室 " + simpleDateFormat.format(new Date()) + "\n");
        System.out.println("当前channelGroup 大小：" + channelGroup.size());
    }

    // 处于活动状态 即 某某某上线了
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线了~");
    }

    // 处于不活动状态 即 某某某下线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 下线了~");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(item -> {
            if (channel != item) {
                // 不是自己 则发送消息
                item.writeAndFlush("[客户端]" + item.remoteAddress() + "：" + msg);
            } else {
                item.writeAndFlush("[我]：" + msg);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器出错了..." + cause);
        ctx.close();
    }
}
