package com.zhangxiaofanfan.rpc.handler;

import com.zhangxiaofanfan.rpc.message.RpcResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 下午12:11
 * @description TODO
 */
public class RpcResponseMessageHandler extends SimpleChannelInboundHandler<RpcResponseMessage> {
    public static ConcurrentHashMap<Integer, Promise<Object>> PROMISES = new ConcurrentHashMap<>();

    /**
     * 1. RpcResponseMessageHandler 客户端在接收到响应消息时触发
     * 2. 将数据注入到会话id对应的 Promise 对象中, 唤醒客户端等待线程, 执行后续操作
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponseMessage msg) throws Exception {
        // 1. 获取用来进行线程通信的 Promise 对象
        Promise<Object> promise = PROMISES.remove(msg.getSessionId());
        // 2. 对 promise 进行设置
        if (promise != null) {
            Object returnValue = msg.getReturnValue();
            Exception exceptionValue = msg.getExceptionValue();
            if (exceptionValue != null) {
                promise.setFailure(exceptionValue);
            } else {
                promise.setSuccess(returnValue);
            }
        }
    }
}
