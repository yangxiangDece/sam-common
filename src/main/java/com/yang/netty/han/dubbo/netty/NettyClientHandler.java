package com.yang.netty.han.dubbo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    // 上下文
    private ChannelHandlerContext context;
    // 返回结果
    private String result;
    // 客户端调用方法时，传入的参数
    private String param;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        synchronized (this) {
            result = msg.toString();
            notify();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出错了..." + cause);
        ctx.close();
    }

    // 被代理对象调用，发送数据给服务器 -> wait -> 等待被唤醒() -> 返回结果
    @Override
    public Object call() throws Exception {
        synchronized (this) {
            context.writeAndFlush(param);
            // 等待服务器结果 会在 channelRead 方法唤醒
            wait();
            return result;
        }
    }

    public void setParam(String param) {
        this.param = param;
    }
}
