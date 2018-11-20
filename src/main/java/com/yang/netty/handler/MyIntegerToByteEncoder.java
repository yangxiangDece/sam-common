package com.yang.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
*
* @description 编码器，出栈处理器，将数据处理成字节流，可以在网络中传输
* @author YangXiang
* @time 2018/11/7 21:28
*
*/
public class MyIntegerToByteEncoder extends MessageToByteEncoder<Integer> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
        System.out.println("encode invoke!");
        out.writeInt(msg);
    }
}
