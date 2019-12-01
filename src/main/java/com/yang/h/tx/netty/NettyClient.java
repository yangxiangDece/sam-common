package com.yang.h.tx.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class NettyClient implements InitializingBean {

    public NettyClientHandler client = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        start("localhost", 8080);
    }

    private void start(String hostName, int port) throws InterruptedException {
        client = new NettyClientHandler();

        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast("handler", client);
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect(hostName, port).sync();
        channelFuture.channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
    }

    public void send(JSONObject jsonObject) {
        client.call(jsonObject);
    }
}
