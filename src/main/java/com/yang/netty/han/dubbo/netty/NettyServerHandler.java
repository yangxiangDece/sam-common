package com.yang.netty.han.dubbo.netty;

import com.yang.netty.han.dubbo.ProtocolConstants;
import com.yang.netty.han.dubbo.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead");
        // 获取客户端发送的消息 并调用服务提供者的服务
        System.out.println("msg：" + msg);
        // 客户端在调用服务器的api时，我们需要定义一个协议
        if (msg.toString().startsWith(ProtocolConstants.protocol_name)) {
            String hello = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(hello);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出错了..." + cause);
        ctx.close();
    }
}
