package com.yang.netty.han.inoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LongInboundClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("收到数据：" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(123456L); // 发送一个Long类型的数据

        // 处理的是Long类型，这里传入其他类型 MyByteToLongDecoder 只会按照8个、8个的字节地来处理
//        ctx.writeAndFlush(Unpooled.copiedBuffer("a b c d e f g", StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出错了..." + cause);
        ctx.channel().close();
    }
}
