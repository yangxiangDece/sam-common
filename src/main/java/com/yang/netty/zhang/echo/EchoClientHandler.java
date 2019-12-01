package com.yang.netty.zhang.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 客户端业务处理
 */
@ChannelHandler.Sharable // 标识一个ChannelHandler 可以被多个Channel安全地共享
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    // 在到服务器的连接已经建立之后将被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当与服务器连接成功后，马上就发送消息到服务器
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks", CharsetUtil.UTF_8));
    }

    // 当从服务器接收到一条消息时被调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // 接收服务器发送过来的数据
        System.out.println("Client received：" + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
