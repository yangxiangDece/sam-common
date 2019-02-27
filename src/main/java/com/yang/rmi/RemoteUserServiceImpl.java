package com.yang.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 请注意这里继承的是UnicastRemoteObject父类。
 * 继承于这个父类，表示这个Remote Object是“存在于本地”的RMI服务实现
 *
 * 一般来说RMI Server的实现可以继承两种父类：UnicastRemoteObject和Activatable
 * 前者的意义是，RMI Server真实的服务提供者将工作在“本地JVM”上；后者的意义是，RMI Server的真实服务提供者，不是在“本地JVM”上运行，
 * 而是可以通过“RMI Remote Server 激活”技术，被序列化到“远程JVM”（即远程RMI注册表所在的JVM上），并适时被“远程JVM”加载运行。
 */
public class RemoteUserServiceImpl extends UnicastRemoteObject implements RemoteUserService {

    private static final long serialVersionUID = -2823577004825352979L;

    protected RemoteUserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<UserInfo> queryAllUserInfo() throws RemoteException {

        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo(1L,"张三",23));
        list.add(new UserInfo(2L,"李四",25));

        return list;
    }
}
