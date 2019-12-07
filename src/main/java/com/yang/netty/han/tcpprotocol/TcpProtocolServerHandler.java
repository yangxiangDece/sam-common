package com.yang.netty.han.tcpprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TcpProtocolServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int num = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] bytes = msg.getMessage();
        System.out.println("服务器接收到的数据：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(bytes, 0, len, StandardCharsets.UTF_8));
        System.out.println("服务器接收到消息总量：" + (++this.num));

        // 回复客户端消息
        byte[] responseMessage = (" " + UUID.randomUUID().toString()).getBytes(StandardCharsets.UTF_8);
        int responseLen = responseMessage.length;
        MessageProtocol responseMessageProtocol = new MessageProtocol(responseLen, responseMessage);
        ctx.writeAndFlush(responseMessageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出错了..." + cause);
        ctx.channel().close();
    }
}
