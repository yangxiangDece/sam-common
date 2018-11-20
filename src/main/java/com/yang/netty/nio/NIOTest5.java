package com.yang.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest5 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile=new RandomAccessFile("nio_file/mapper_buffer.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'c');
        mappedByteBuffer.put(4,(byte)'A');

        fileChannel.close();
        randomAccessFile.close();
    }
}
