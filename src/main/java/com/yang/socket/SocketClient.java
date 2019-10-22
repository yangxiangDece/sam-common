package com.yang.socket;

import com.yang.dubbo.singlelongconnection.utils.CloseStreamUtil;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws Exception {
        Socket socket = null;
        BufferedWriter bufferedWriter = null;
        try {
            socket = new Socket("localhost", 9999);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("你好啊！");
            bufferedWriter.newLine();
            bufferedWriter.write("我只会在");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            socket.shutdownOutput();
        } finally {
            CloseStreamUtil.close(socket, bufferedWriter);
        }
    }
}
