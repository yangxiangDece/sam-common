package com.yang.netty.zhang.zerocopy;

import java.io.DataOutputStream;
import java.net.Socket;

public class OldClient {

    public static void main(String[] args) throws Exception {

        Socket socket=new Socket("localhost",9999);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());

        dataOutputStream.write("大家好，我是渣渣辉！".getBytes("UTF-8"));
        dataOutputStream.flush();
        dataOutputStream.close();
        socket.close();
    }
}
