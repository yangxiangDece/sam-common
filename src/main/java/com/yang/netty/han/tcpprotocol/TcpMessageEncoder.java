package com.yang.netty.han.tcpprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TcpMessageEncoder extends MessageToByteEncoder<MessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("TcpMessageEncoder encode 被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getMessage());
    }
}
