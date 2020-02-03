package com.yang.netty.bio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BIOClient {

    private final static int port = 8085;

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(port));
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好啊，我是客户端".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        System.out.println("发送数据成功...");
        outputStream.close();
    }
}
