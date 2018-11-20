package com.yang.netty.nio;

import java.nio.ByteBuffer;

public class NIOTest4 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(64);

        byteBuffer.putInt(22);
        byteBuffer.putInt(23);
        byteBuffer.putLong(2311111L);

        byteBuffer.flip();

        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getInt());
    }
}
