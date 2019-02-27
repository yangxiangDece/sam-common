package com.yang.rmi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class RemoteClient {

    private static final Log LOGGER = LogFactory.getLog(RemoteClient.class);

    static {
        BasicConfigurator.configure();
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        //这里使用的是java名称服务技术进行的RMI接口查找。
        RemoteUserService remoteUserService = (RemoteUserService) Naming.lookup("rmi://127.0.0.1/queryAllUserInfo");

        List<UserInfo> list = remoteUserService.queryAllUserInfo();

        LOGGER.info(list);
    }
}
