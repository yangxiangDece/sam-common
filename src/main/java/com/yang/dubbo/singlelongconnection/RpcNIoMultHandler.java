package com.yang.dubbo.singlelongconnection;

import com.yang.dubbo.singlelongconnection.utils.SerializeUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * RPC实际代理类
 */
public class RpcNIoMultHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 获取请求id
        Long reponseId = RpcContainer.getRequestId();

        // 封装请求对象
        RequestMultObject requestMultObject = new RequestMultObject(method.getDeclaringClass(), method.getName(), method.getParameterTypes(), args);
        requestMultObject.setRequestId(reponseId);

        // 封装设置RpcResponseFuture，主要用于获取返回值
        RpcResponseFuture rpcResponseFuture = new RpcResponseFuture(reponseId);
        RpcContainer.addRequstFuture(rpcResponseFuture);

        // 序列化请求信息
        byte[] bytes = SerializeUtil.serialize(requestMultObject);
        // 发送请求信息 正式调用远程接口
        RpcNioMultClient.getInstance().sendMsgToServer(bytes);

        // 从ResponseContainer获取返回值 如果服务端还未执行完 这里会阻塞将现线程挂起 知道有结果返回
        byte[] responseBytes = rpcResponseFuture.get();
        if (responseBytes != null) {
            // 这一次请求处理完成
            RpcContainer.removeResponseAndFuture(reponseId);

            // 反序列化返回结果
            Object result = SerializeUtil.unSerialize(responseBytes);
            System.out.println("请求id：" + reponseId + " 结果：" + result);
            return result;
        }
        return null;
    }
}
