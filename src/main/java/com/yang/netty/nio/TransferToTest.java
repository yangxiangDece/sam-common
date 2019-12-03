package com.yang.netty.nio;

import com.yang.dubbo.singlelongconnection.utils.CloseStreamUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class TransferToTest {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D:/a.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("D:/b.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        inputChannel.transferTo(0, inputChannel.size(), outputChannel);

        CloseStreamUtil.close(inputChannel, outputChannel, fileInputStream, fileOutputStream);
    }
}
