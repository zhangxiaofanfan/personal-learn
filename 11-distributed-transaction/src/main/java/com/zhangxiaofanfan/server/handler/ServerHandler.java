package com.zhangxiaofanfan.server.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-31 上午11:15
 * @description 服务器端处理器
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static Log LOG = LogFactory.get();
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    // 事务组中事务状态列表
    private static Map<String, List<String>> transactionTypeMap = new HashMap<>();
    // 事务组中是否已经接收到结束的标志
    private static Map<String, Boolean> isEndMap = new HashMap<>();
    // 事务组中应该有的事务个数
    private static Map<String, Integer> transactionCountMap = new HashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(ctx.channel());
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final String CRETE_STR = "create";
        final String ADD_STR = "add";

        LOG.debug("接收数据: {}", msg);

        JSONObject jsonObject = JSONUtil.parseObj((String)msg);
        // created --> 创建事务组, add --> 添加分支事务
        String command = jsonObject.getStr("command");
        // 事务组id
        String groupId = jsonObject.getStr("groupId");
        // 子事务类型 commit --> 待提交, rollback --> 带回滚
        String transactionType = jsonObject.getStr("transactionType");
        // 事务数量
        Integer transactionCount = jsonObject.getInt("transactionCount");
        // 是否结束事务
        Boolean isEnd = jsonObject.getBool("isEnd");

        if (CRETE_STR.equals(command)) {
            // 创建事务组
            transactionTypeMap.put(groupId, new ArrayList<>());
        } else if (ADD_STR.equals(command)) {
            // 加入事务组
            transactionTypeMap.get(groupId).add(transactionType);

            if (isEnd) {
                isEndMap.put(groupId, true);
                transactionCountMap.put(groupId, transactionCount);
            }

            JSONObject result = new JSONObject();
            result.set("groupId", groupId);

            // 如果已经接收到结束事务的标志, 比较事务是否已经全部到达, 如果全部到达校验是否需要回滚
            if (isEndMap.get(groupId) && transactionCountMap.get(groupId).equals(transactionTypeMap.get(groupId).size())) {
                // if (transactionTypeMap.get(groupId).contains("rollback")) {
                //     result.set("command", "rollback");
                // } else {
                //     result.set("command", "commit");
                // }
                result.set("command", transactionTypeMap.get(groupId).contains("rollback") ? "rollback" : "commit");
                sendResult(result);
            }
        }
    }

    /**
     * 向注册在服务器端的所有客户端发送数据
     * @param result 需要发送的数据
     */
    private void sendResult(JSONObject result) {
        for (Channel channel : channelGroup) {
            LOG.debug("发送数据: {}", result);
            channel.writeAndFlush(result);
        }
    }
}
