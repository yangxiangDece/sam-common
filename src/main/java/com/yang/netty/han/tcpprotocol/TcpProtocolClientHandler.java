package com.yang.netty.han.tcpprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class TcpProtocolClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int num = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 5; i++) {
            byte[] message = ("力拔三兮气盖世，时不利兮骓不逝" + i + "").getBytes(StandardCharsets.UTF_8);
            int len = message.length;
            MessageProtocol messageProtocol = new MessageProtocol(len, message);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] bytes = msg.getMessage();
        System.out.println("客户端接收到的数据：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(bytes, 0, len, StandardCharsets.UTF_8));
        System.out.println("客户端接收到消息总量：" + (++this.num));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出错了..." + cause);
        ctx.channel().close();
    }
}
