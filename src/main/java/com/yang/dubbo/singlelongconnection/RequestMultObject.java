package com.yang.dubbo.singlelongconnection;

import java.io.Serializable;

/**
 * 请求时的数据结构
 */
public class RequestMultObject implements Serializable {
    private static final long serialVersionUID = 7298022757862473471L;

    // 请求id
    private Long requestId;

    // 服务提供者接口class
    private Class<?> clazz;

    // 服务的方法名称
    private String methodName;

    // 参数类型
    private Class<?>[] paramTypes;

    // 参数值
    private Object[] arguments;

    public RequestMultObject(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object[] arguments) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.arguments = arguments;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
