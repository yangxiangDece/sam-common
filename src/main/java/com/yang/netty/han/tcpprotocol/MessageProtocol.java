package com.yang.netty.han.tcpprotocol;

public class MessageProtocol {

    private int len;
    private byte[] message;

    public MessageProtocol(int len, byte[] message) {
        this.len = len;
        this.message = message;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }
}
