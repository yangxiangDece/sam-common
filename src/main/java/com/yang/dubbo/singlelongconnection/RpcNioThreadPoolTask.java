package com.yang.dubbo.singlelongconnection;

import com.yang.dubbo.singlelongconnection.utils.ThreadPoolUtil;

public class RpcNioThreadPoolTask {

    public static void addTask(RpcNioMultServerTask task) {
        ThreadPoolUtil.getThreadPoolExecutor().execute(task);
    }
}
