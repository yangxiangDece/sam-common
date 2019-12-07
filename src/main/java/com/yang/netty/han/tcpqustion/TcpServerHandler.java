package com.yang.netty.han.tcpqustion;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TcpServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int num = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String message = new String(bytes, 0, bytes.length, StandardCharsets.UTF_8);
        System.out.println("获取到数据：" + message);
        num++;
        System.out.println("服务器接收到消息量=" + this.num);

        // 回复客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer(UUID.randomUUID().toString() + " ", StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出错了..." + cause);
        ctx.channel().close();
    }
}
