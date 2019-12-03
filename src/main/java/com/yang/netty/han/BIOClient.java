package com.yang.netty.han;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

public class BIOClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8085));
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好啊，我是客户端".getBytes(Charset.forName("UTF-8")));
        outputStream.flush();
        System.out.println("发送数据成功...");
        outputStream.close();
    }
}
