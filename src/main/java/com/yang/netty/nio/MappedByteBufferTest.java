package com.yang.netty.nio;

import com.yang.dubbo.singlelongconnection.utils.CloseStreamUtil;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 可以让文件直接在内存中（堆外内存）修改，操作系统不需要将文件从内核空间拷贝到用户空间 性能很高
 * 同时MappedByteBuffer也是不受JVM控制的，所以有可能代码返回成功了，但是文件还在拷贝中
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("nio_file/mapper_buffer.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'c');
        mappedByteBuffer.put(4, (byte) 'A');

        CloseStreamUtil.close(fileChannel, randomAccessFile);
    }
}
