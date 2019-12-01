package com.yang.h.tx.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yang.h.tx.enums.CommandType;
import com.yang.h.tx.enums.TransactionType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static Map<String, List<String>> transactionIdMap = new HashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收数据：" + msg.toString());

        JSONObject jsonObject = JSON.parseObject((String) msg);
        String command = jsonObject.getString("command");// create-开启事务，registry-注册分支事务，commit-提交事务
        String groupId = jsonObject.getString("groupId");// 事务组id
        String transactionType = jsonObject.getString("transactionType");// 分支事务类型，commit-提交，rollback-回滚
        String transactionId = jsonObject.getString("transactionId");// 分支事务id

        if (CommandType.create.name().equals(command)) {
            // 1、开启全局事务
            transactionIdMap.put(groupId, new ArrayList<>());
        } else if (CommandType.registry.name().equals(command)) {
            // 2、注册分支事务
            transactionIdMap.get(groupId).add(transactionId);
            // 3、注册过程中 发现有事务需要回滚
            if (TransactionType.rollback.name().equals(transactionType)) {
                System.out.println("接收到了一个回滚状态");
                sentMsg(groupId, TransactionType.rollback.name()); // 整个事务回滚
            }
        } else if (CommandType.commit.name().equals(command)) {
            // 4、接收到TM发的提交全局事务
            System.out.println("全局事务提交");
            sentMsg(groupId, CommandType.commit.name());
        }
    }

    private void sentMsg(String groupId, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId", groupId);
        jsonObject.put("transactionType", status);
        channelGroup.writeAndFlush(jsonObject);
    }
}
