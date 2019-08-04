package com.yang.netty.book.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 服务器业务处理
 */
@ChannelHandler.Sharable // 标识一个ChannelHandler 可以被多个Channel安全地共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // 对于每个消息的传入都要调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server received：" + byteBuf.toString(CharsetUtil.UTF_8));
        // 将收到的消息写回给发送者，而不冲刷出站消息
        ctx.write(byteBuf);
    }

    // 通知ChannelInboundHandlerAdapter最后一次对channelRead()的调用是当前批量读取中的最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点，并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常
        cause.printStackTrace();
        // 关闭该Channel
        ctx.close();
    }
}
