package com.yang.netty.video.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ReactorTest {

    public static void main(String[] args) {

    }
}
class Reactor implements Runnable {

    private final Selector selector;
    private final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector=Selector.open();
        serverSocket=ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    /**
     * 也可以使用spi的方式
     * SelectorProvider p=SelectorProvider.service();
     * selector=p.openSelector();
     * serverSocket=p.openServerSocketChannel();
     */

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    dispatch(selectionKey);
                }
                selectionKeys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void dispatch(SelectionKey k){
        Runnable r = (Runnable) k.attachment();
        if(r!=null){
            r.run();
        }
    }

    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if(c!=null){
                    new Handler(selector,c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class Handler implements Runnable {

    private final SelectionKey sk;
    private final SocketChannel socket;
    private ByteBuffer input=ByteBuffer.allocate(1024);
    private ByteBuffer output=ByteBuffer.allocate(1024);
    private static final int READING=0,SENDING=1;
    private int state=READING;

    Handler(Selector selector,SocketChannel socketChannel) throws IOException{
        this.socket=socketChannel;
        socket.configureBlocking(false);
        sk=socket.register(selector,0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if(state==READING){
                read();
            } else if (state==SENDING){
                send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean inputIsComplete(){
        System.out.println("input action!");
        return true;
    }
    private boolean outputIsComplete(){
        System.out.println("output action!");
        return true;
    }
    private void process(){
        System.out.println("process!");
    }

    private void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()){
            process();
            state= SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }
    private void send() throws IOException {
        socket.write(output);
        if(outputIsComplete()){
            sk.cancel();
        }
    }
}