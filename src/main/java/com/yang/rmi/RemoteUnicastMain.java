package com.yang.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 注册表在本机jvm中
 */
public class RemoteUnicastMain {

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        /**
         *  Locate registry，您可以理解成RMI服务注册表，或者是RMI服务位置仓库。
         *  主要的作用是维护一个“可以正常提供RMI具体服务的所在位置”。
         *  每一个具体的RMI服务提供者，都会将自己的Stub注册到Locate registry中，以表示自己“可以提供服务”
         *
         *  有两种方式可以管理Locate registry，一种是通过操作系统的命令行启动注册表；
         *  另一种是在代码中使用LocateRegistry类。
         *
         *  LocateRegistry类中有一个createRegistry方法，可以在这台物理机上创建一个“本地RMI注册表”
         */

        Registry registry = LocateRegistry.createRegistry(1099);

        //向LocateRegistry注册（绑定/重绑定）RMI Server实现。
        RemoteUserServiceImpl remoteUserService=new RemoteUserServiceImpl();
        //通过java 名字服务技术，可以将具体的RMI Server实现绑定一个访问路径。注册到LocateRegistry中
//        Naming.rebind("rmi://127.0.0.1:1099/queryAllUserInfo",remoteUserService);
        registry.rebind("queryAllUserInfo",remoteUserService);
        System.out.println("rmi server is ready");

        /**
         * “Naming.rebind”和“Naming.bind”的区别。
         * 前者是指“重绑定”，如果“重绑定”时“RMI 注册表”已经有了这个服务name的存在，则之前所绑定的Remote Object将会被替换；
         * 后者在执行时如果“绑定”时“RMI注册表”已经有这个服务name的存在，则系统会抛出错误。所以除非您有特别的业务要求，那么建议使用rebind方法进行Remote Object绑定。
         */
    }
}
