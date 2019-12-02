package com.yang.netty.zhang.nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NIOTest6 {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(9999);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int read = 0;
            while (read < messageLength) {
                long r = socketChannel.read(byteBuffers);
                read += r;
                System.out.println("read:" + read);

                Arrays.stream(byteBuffers).map(buffer -> "position:" + buffer.position() + "," + buffer.limit()).forEach(System.out::println);
            }
            Arrays.stream(byteBuffers).forEach(Buffer::flip);
            long write = 0;
            while (write < messageLength) {
                long r = socketChannel.write(byteBuffers);
                write += r;
            }
            Arrays.stream(byteBuffers).forEach(Buffer::clear);

            System.out.println("read:" + read + ",write:" + write + ",messageLength:" + messageLength);
        }
    }
}
