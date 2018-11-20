package com.yang.netty.zerocopy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(9999);

        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder=new StringBuilder();
        String content;
        while ((content=bufferedReader.readLine()) !=null) {
            builder.append(content);
        }
        System.out.println("content:"+builder.toString());
        bufferedReader.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
