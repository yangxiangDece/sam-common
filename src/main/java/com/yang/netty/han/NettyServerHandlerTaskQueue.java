package com.yang.netty.han;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class NettyServerHandlerTaskQueue extends ChannelInboundHandlerAdapter {

    private static EventExecutorGroup eventExecutors = new DefaultEventExecutorGroup(8);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // taskQueue 与 scheduleTaskQueue

        // 如果这里有一个比较耗时的业务，如何处理？
        // 使用用户自定义的普通任务  这样处理后会将任务放到 Channel 里的 NioEventLoop 的 taskQueue 队列中
        ctx.channel().eventLoop().execute(() -> langTime("A"));
        // 可以增加多个 但是taskQueue中的任务是串行执行的 因为只有一个线程 即NioEventLoop
        ctx.channel().eventLoop().execute(() -> langTime("B"));

        eventExecutors.execute(() -> langTime("F"));

        // 定时任务
        // 通过schedule方法指定定时任务
        // 定时任务会被放到 Channel 里的 NioEventLoop 的 scheduleTaskQueue 队列中
        // 但是 scheduleTaskQueue 中的任务和taskQueue 中的任务 是使用的一个线程，所以都是串行的，
        // 那么如果taskQueue里的任务执行时间过长 则 scheduleTaskQueue里的任务将无法按时执行任务，会出现延时的问题
        ctx.channel().eventLoop().schedule(() -> langTime("C"), 5, TimeUnit.SECONDS);

        System.out.println("服务器线程：" + Thread.currentThread().getName());
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("NettyServer：" + byteBuf.toString(StandardCharsets.UTF_8));
        System.out.println("NettyServer：" + ctx.channel().remoteAddress());
    }

    private void langTime(String tag) {
        try {
            // 模拟耗时业务
            TimeUnit.SECONDS.sleep(5);
            System.out.println("耗时业务执行完成..." + tag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("你也好啊，客户端", StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("NettyServer：出异常了...");
        ctx.close();
    }
}
