package com.yang.designmode.proxy.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    private static final long serialVersionUID = -5935951824635155872L;

    protected MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server says, hey";
    }

    public static void main(String[] args) throws Exception {
        MyRemote service = new MyRemoteImpl();
        Naming.rebind("RemoteHello", service);
    }
}
