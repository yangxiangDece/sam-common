package com.yang.netty.han.inoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * 会根据接收到的数据 被调用多次，比如数据有16个字节 那么这里就会被调用两次 直到确定没有新的数据添加到list，或者是byteBuf 没有更多的可读字节为止
     * 如果list不为空，那么就会将list传递给下一个ChannelInboundHandler ,并且也会被调用多次，一个链一直调用直到最后一个ChannelInboundHandler
     *
     * @param ctx 上下文对象
     * @param in  入栈的byteBuf
     * @param out 将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder decode 被调用");
        // Long 8个字节
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
