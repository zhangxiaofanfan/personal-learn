package com.zhangxiaofanfan.rpc.handler;

import com.zhangxiaofanfan.rpc.annotation.MySharable;
import com.zhangxiaofanfan.rpc.message.RpcRequestMessage;
import com.zhangxiaofanfan.rpc.message.RpcResponseMessage;
import com.zhangxiaofanfan.rpc.service.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 下午12:10
 * @description TODO
 */
@MySharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {
    private final static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    static {
        map.put("com.zhangxiaofanfan.rpc.service.HelloService", "com.zhangxiaofanfan.rpc.service.impl.HelloServiceImpl");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) {
        RpcResponseMessage response = new RpcResponseMessage();
        response.setSessionId(msg.getSessionId());

        // 通过反射创建 HelloService 对象
        try {
            HelloService helloService = (HelloService)Class.forName(map.get(msg.getInterfaceName())).newInstance();
            Method method = helloService.getClass().getMethod(msg.getMethodName(), msg.getParameterType());
            // 使用反射机制调用代理方法
            Object invoke = method.invoke(helloService, msg.getParameterValue());
            response.setReturnValue(invoke);
        } catch (Exception e) {
            e.printStackTrace();
            response.setExceptionValue(new Exception("远程调用出错"));
        }
        ctx.writeAndFlush(response);
    }
}
