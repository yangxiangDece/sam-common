package com.yang.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteUserService extends Remote {

    List<UserInfo> queryAllUserInfo() throws RemoteException;
}
