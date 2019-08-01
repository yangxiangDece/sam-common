package com.yang.dubbo.singlelongconnection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * RPC容器 用来存储发送RPC请求时的请求对象，以及存储返回值
 */
public class RpcContainer {

    // 返回值容器
    private final static Map<Long, byte[]> responseContainer = new ConcurrentHashMap<>();

    // 请求对象容器
    private final static Map<Long, RpcResponseFuture> requestFuture = new ConcurrentHashMap<>();

    // 请求id
    private volatile static AtomicLong requestId = new AtomicLong(0);

    public static Long getRequestId() {
        return requestId.getAndIncrement();
    }

    public static void addResponse(Long requestId, byte[] responseBytes) {
        responseContainer.put(requestId, responseBytes);
        requestFuture.get(requestId).rpcIsDone();
    }

    public static byte[] getResponse(Long requestId) {
        return responseContainer.get(requestId);
    }

    public static void addRequstFuture(RpcResponseFuture rpcResponseFuture) {
        requestFuture.put(rpcResponseFuture.getRequestId(), rpcResponseFuture);
    }

    public static void removeResponseAndFuture(Long requestId) {
        responseContainer.remove(requestId);
        requestFuture.remove(requestId);
    }
}
