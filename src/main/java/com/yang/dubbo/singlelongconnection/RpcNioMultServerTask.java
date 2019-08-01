package com.yang.dubbo.singlelongconnection;

import com.yang.dubbo.singlelongconnection.utils.SerializeUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 用于获取数据执行远端方法的线程池任务类
 */
public class RpcNioMultServerTask implements Runnable {

    private byte[] bytes;
    private SocketChannel channel;

    public RpcNioMultServerTask(byte[] bytes, SocketChannel channel) {
        this.bytes = bytes;
        this.channel = channel;
    }

    @Override
    public void run() {
        if (bytes != null && bytes.length > 0 && channel != null) {
            // 反序列化请求信息
            RequestMultObject requestMultObject = (RequestMultObject) SerializeUtil.unSerialize(bytes);
            // 调用服务并序列化结果返回
            requestHandle(requestMultObject, channel);
        }
    }

    private void requestHandle(RequestMultObject requestMultObject, SocketChannel channel) {
        if (requestMultObject == null) {
            throw new NullPointerException();
        }
        Long requestId = requestMultObject.getRequestId();
        Object obj = BeanContainer.getBean(requestMultObject.getClazz());
        String methodName = requestMultObject.getMethodName();
        Class<?>[] paramTypes = requestMultObject.getParamTypes();
        Object[] arguments = requestMultObject.getArguments();
        try {
            Method method = obj.getClass().getMethod(methodName, paramTypes);
            String result = (String) method.invoke(obj, arguments);
            // 序列化方法执行结果
            byte[] bytes = SerializeUtil.serialize(result);
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length + 12);
            // 为了便于客户端获得请求ID，直接将id写在头部（这样客户端直接解析即可获得，不需要将所有消息反序列化才能得到）
            // 然后写入消息题的长度，最后写入返回内容
            buffer.putLong(requestId);
            buffer.putInt(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }
}
