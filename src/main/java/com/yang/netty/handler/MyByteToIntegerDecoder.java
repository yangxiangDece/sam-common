package com.yang.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
*
* @description 解码器，入栈处理器，将网络中字节流转换成自己需要的数据
* @author YangXiang
* @time 2018/11/7 21:23
*
*/
public class MyByteToIntegerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode invoke!");
        System.out.println("readableBytes:"+in.readableBytes());
        if(in.readableBytes()>=4){
            out.add(in.readInt());
        }
    }
}
