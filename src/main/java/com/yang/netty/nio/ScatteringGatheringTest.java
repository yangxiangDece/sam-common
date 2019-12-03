package com.yang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scatting：将数据写入到buffer时，可以采用buffer数组，依次写入
 * Gathering：从buffer读取数据时，可以采用buffer数组，依次读取
 */
public class ScatteringGatheringTest {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
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
                // 打印buffers数组中的信息
                Arrays.stream(byteBuffers).map(buffer -> "position:" + buffer.position() + "," + buffer.limit()).forEach(System.out::println);
            }
            // 将所有的buffer进行filip
            Arrays.stream(byteBuffers).forEach(Buffer::flip);
            // 将数据读出显示到客户端
            long write = 0;
            while (write < messageLength) {
                long r = socketChannel.write(byteBuffers);
                write += r;
            }
            // 将所有的buffer进行clear
            Arrays.stream(byteBuffers).forEach(Buffer::clear);
            System.out.println("read:" + read + ",write:" + write + ",messageLength:" + messageLength);
        }
    }
}
