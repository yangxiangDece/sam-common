package com.yang.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest3 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("nio_file/input.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("nio_file/out.txt");

        FileChannel inputChannel=fileInputStream.getChannel();
        FileChannel outputChannel=fileOutputStream.getChannel();

//        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        //直接内存
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);
        while ((inputChannel.read(byteBuffer)) !=-1){
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        inputChannel.close();
        outputChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
