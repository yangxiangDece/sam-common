package com.yang.netty.zhang.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest1 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("nio_file/NioFileTest1.txt");
        FileChannel fileChannel=fileInputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println("Character:");
        while (byteBuffer.hasRemaining()){
            System.out.print((char)byteBuffer.get());
        }
        fileChannel.close();
        fileInputStream.close();
    }
}
