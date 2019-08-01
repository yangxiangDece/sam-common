package com.yang.dubbo.singlelongconnection;

import com.yang.dubbo.singlelongconnection.service.HelloService;
import com.yang.dubbo.singlelongconnection.utils.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

/**
 * 客户端RPC调用
 */
public class RpcNioConsumer {

    public static void main(String[] args) {
        multipartRpcNio();
    }

    private static void multipartRpcNio() {
        HelloService helloService = RpcProxyFactory.getMultService(HelloService.class);
        ExecutorService executorService = ThreadPoolUtil.getThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.execute(() -> {
                String result = helloService.hello("zhangsan" + finalI);
                System.out.println("执行结果" + finalI + "：" + result);
            });
        }
    }
}
