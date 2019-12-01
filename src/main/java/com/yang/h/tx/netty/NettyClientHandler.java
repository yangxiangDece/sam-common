package com.yang.h.tx.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yang.h.tx.GlobalTransactionManager;
import com.yang.h.tx.MyTransaction;
import com.yang.h.tx.enums.TransactionType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收数据：" + msg.toString());

        JSONObject jsonObject = JSON.parseObject((String) msg);
        String command = jsonObject.getString("command");// create-开启事务，registry-注册分支事务，commit-提交事务
        String groupId = jsonObject.getString("groupId");// 事务组id

        System.out.println("接收command：" + command);

        MyTransaction transaction = GlobalTransactionManager.getMyTransaction(groupId);
        if (TransactionType.commit.name().equals(command)) {
            transaction.setTransactionType(TransactionType.commit);
        } else if (TransactionType.rollback.name().equals(command)) {
            transaction.setTransactionType(TransactionType.rollback);
        }
        transaction.getTask().signalTask();
    }

    public void call(JSONObject data) {
        context.writeAndFlush(data.toJSONString()).channel().newPromise();
    }
}
