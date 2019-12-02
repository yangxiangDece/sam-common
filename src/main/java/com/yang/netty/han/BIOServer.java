package com.yang.netty.han;

import com.yang.common.ThreadPoolExecutorFactory;
import com.yang.dubbo.singlelongconnection.utils.CloseStreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class BIOServer {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = ThreadPoolExecutorFactory.getThreadPoolExecutor();

        ServerSocket serverSocket = new ServerSocket(8085);
        System.out.println("服务端已启动...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(() -> handler(socket));
        }
    }

    private static void handler(Socket socket) {
        System.out.println("线程id：" + Thread.currentThread().getId() + ",线程名称：" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseStreamUtil.close(inputStream);
        }
    }
}
