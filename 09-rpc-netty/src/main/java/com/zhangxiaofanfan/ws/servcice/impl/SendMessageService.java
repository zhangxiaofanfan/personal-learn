package com.zhangxiaofanfan.ws.servcice.impl;

import com.zhangxiaofanfan.ws.config.NettyConfig;
import com.zhangxiaofanfan.ws.servcice.ISendMessageService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:44:55
 */
@Slf4j
@Service
public class SendMessageService implements ISendMessageService {

    @Override
    public void sendMsgToUserByUserId(String userId, String receiver, String msg) {
        //根据 UserId 获取对应的Channel
        Channel channel = NettyConfig.getOnlineUserChannelMap().get(receiver);

        if (Objects.isNull(channel)) {
            throw new RuntimeException("UserId：" + receiver + "不存在");
        }

        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(userId + "：" + msg);
        //将消息发送给指定的用户
        channel.writeAndFlush(textWebSocketFrame);
        log.debug(textWebSocketFrame.text());
    }

    @Override
    public void sendMsgToGroup(String msg) {
        //给所有在线的用户发送消息
        NettyConfig.getOnlineChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }

}
