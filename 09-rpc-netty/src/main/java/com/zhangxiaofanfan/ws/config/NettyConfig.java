package com.zhangxiaofanfan.ws.config;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 存储在线的 channel 信息
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:30:02
 */
public class NettyConfig {
    /**
     * 存储所有在线的客户端Channel
     */
    @Getter
    private static final ChannelGroup onlineChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 存储所有在线的UserId与之对应的Channel
     */
    @Getter
    private static final ConcurrentMap<String, Channel> onlineUserChannelMap = new ConcurrentHashMap<>();
}
