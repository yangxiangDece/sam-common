package com.yang.designmode.headfirst.proxy.rmi;

import java.rmi.Naming;

public class MyRemoteClient {

    public static void main(String[] args) throws Exception {
        new MyRemoteClient().go();
    }

    private void go() throws Exception {
        MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
        System.out.println(service.sayHello());
    }
}
