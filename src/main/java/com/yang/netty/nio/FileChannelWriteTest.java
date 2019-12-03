package com.yang.netty.nio;

import com.yang.dubbo.singlelongconnection.utils.CloseStreamUtil;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteTest {

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("nio_file/NioFileTest2.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String content = "ni hao a, i'm super hero!";
        byteBuffer.put(content.getBytes("UTF-8"));
        byteBuffer.flip();
        fileChannel.write(byteBuffer);

        CloseStreamUtil.close(fileOutputStream);
    }
}
