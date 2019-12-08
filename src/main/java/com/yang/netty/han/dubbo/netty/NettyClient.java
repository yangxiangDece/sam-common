package com.yang.netty.han.dubbo.netty;

import com.yang.common.ThreadPoolExecutorFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;

public class NettyClient {

    private static ExecutorService executorService = ThreadPoolExecutorFactory.getThreadPoolExecutor();

    private NettyClientHandler clientHandler;

    private String hostname;
    private int port;

    private NettyClient() {
    }

    public NettyClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public Object getBean(final Class<?> serviceClass, final String protocolName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{serviceClass}, (proxy, method, args) -> {
            if (clientHandler == null) {
                initClient();
            }
            // protocolName 是自定义协议头
            clientHandler.setParam(protocolName + args[0]);
            return executorService.submit(clientHandler).get();
        });
    }

    private void initClient() {
        clientHandler = new NettyClientHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(clientHandler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(hostname, port).sync();
            System.out.println("客户端连接成功...");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
