package com.yang.netty.han.tcpprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class TcpMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("TcpMessageDecoder decode 被调用");
        // 将得到的字节码 转换为 MessageProtocol
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        MessageProtocol messageProtocol = new MessageProtocol(len, bytes);
        out.add(messageProtocol);
    }
}
