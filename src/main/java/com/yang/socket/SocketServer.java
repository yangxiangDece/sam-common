package com.yang.socket;

import com.yang.dubbo.singlelongconnection.utils.CloseStreamUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader bufferedReader = null;
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                socket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                StringBuilder builder = new StringBuilder();
                while ((message = bufferedReader.readLine()) != null) {
                    builder.append(message);
                }
                System.out.println(builder.toString());
                System.out.println(socket.isClosed());
            }
        } finally {
            CloseStreamUtil.close(serverSocket, socket, bufferedReader);
        }
    }
}
