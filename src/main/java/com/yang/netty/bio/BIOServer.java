package com.yang.netty.bio;

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
            printThread();
            System.out.println("等待连接...");
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端：" + socket.hashCode());
            executorService.execute(() -> handler(socket));
        }
    }

    private static void handler(Socket socket) {
        printThread();
        byte[] bytes = new byte[1024];
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            while (true) {
                System.out.println("等待读...");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseStreamUtil.close(inputStream);
        }
    }

    private static void printThread() {
        System.out.println("线程id：" + Thread.currentThread().getId() + ",线程名称：" + Thread.currentThread().getName());
    }
}
