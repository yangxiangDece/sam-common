package com.yang.netty.zhang.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteBufTest {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("张hello world", StandardCharsets.UTF_8);
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();

            System.out.println(new String(content, StandardCharsets.UTF_8));
            System.out.println(byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            int length = byteBuf.readableBytes();
            for (int i = 0; i < length; i++) {
                System.out.println((char) byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(0, 4, StandardCharsets.UTF_8));
            System.out.println(byteBuf.getCharSequence(4, 5, StandardCharsets.UTF_8));
        }

    }
}
