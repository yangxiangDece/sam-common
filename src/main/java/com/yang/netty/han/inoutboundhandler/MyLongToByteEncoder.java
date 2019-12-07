package com.yang.netty.han.inoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    /*
        MessageToByteEncoder 类中的write方法 if (acceptOutboundMessage(msg)) {
        acceptOutboundMessage(msg) 这个方法会判断 当前类型是否是自己要处理的类型，如果是 则会执行encode(ctx, cast, buf); 进行出栈编码处理，
        否则不做任何处理直接传递给下一个handler
        所以 一定要保证出栈和入栈的数据类型一致，否则会影响handler的执行
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder encode 被调用");
        System.out.println("获取到的数据：" + msg);
        out.writeLong(msg);
    }
}
