package com.yang.netty.zhang.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class UnicodeTest {

    public static void main(String[] args) throws Exception {
        String inputFile="nio_file/unicode_test_input.txt";
        String outputFile="nio_file/unicode_test_input.txt";
        RandomAccessFile inputRandomAccessFile=new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandomAccessFile=new RandomAccessFile(outputFile,"rw");

        FileChannel inputChannel=inputRandomAccessFile.getChannel();
        FileChannel outputChannel=outputRandomAccessFile.getChannel();

        long inputLength=new File(inputFile).length();

        MappedByteBuffer inputData=inputChannel.map(FileChannel.MapMode.READ_WRITE,0,inputLength);

        Charset charset=Charset.forName("iso-8859-1");
        CharsetDecoder decoder=charset.newDecoder();
        CharsetEncoder encoder=charset.newEncoder();

        CharBuffer charBuffer=decoder.decode(inputData);
        ByteBuffer outputBuffer=encoder.encode(charBuffer);

        outputChannel.write(outputBuffer);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
    }
}
