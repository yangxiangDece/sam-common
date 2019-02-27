package com.yang.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 注册表在远程jvm中
 */
public class RemoteRegistryUnicastMain {

    public static void main(String[] args) throws RemoteException {

        //通过LocateRegistry的get方法，寻找一个存在于远程JVM上的RMI注册表
        Registry registry= LocateRegistry.getRegistry("192.168.10.159",1099);

        //以下是向远程RMI注册表（绑定/重绑定）RMI Server的Stub。
        //同样的远程RMI注册表的JVM-classpath下，一定要有这个RMI Server的Stub
        RemoteUserServiceImpl remoteUserService=new RemoteUserServiceImpl();

        //注册 远程RMI注册表 注册的RMI注册表存在于192.168.10.159这个IP上
        //使用注册表registry进行绑定或者重绑定时，不需要写完整的RMI URL
        registry.rebind("queryAllUserInfo",remoteUserService);
    }
}
