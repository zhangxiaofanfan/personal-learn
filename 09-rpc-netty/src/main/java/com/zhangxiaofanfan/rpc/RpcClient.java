package com.zhangxiaofanfan.rpc;

import com.zhangxiaofanfan.rpc.generator.SessionIdGenerator;
import com.zhangxiaofanfan.rpc.handler.RpcResponseMessageHandler;
import com.zhangxiaofanfan.rpc.message.RpcRequestMessage;
import com.zhangxiaofanfan.rpc.protocol.MessageCodecSharable;
import com.zhangxiaofanfan.rpc.protocol.ProtocolFrameDecoder;
import com.zhangxiaofanfan.rpc.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;

import java.lang.reflect.Proxy;
import java.util.Scanner;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 下午4:56
 * @description RPC 远程调用客户端
 */
public class RpcClient {
    private static Channel channel = null;
    private static final Object LOCK = new Object();
    private static final String HOST_NAME = "localhost";
    private static final Integer HOST_PORT = 8001;

    public static void main(String[] args) {
        HelloService service = getProxyService(HelloService.class);
        Scanner scanner = new Scanner(System.in);
        final String quitStr = "quit";
        while (true) {
            System.out.print("please input: ");
            String input = scanner.nextLine();
            if (quitStr.equals(input))
                break;
            System.out.println(service.hello(input));
        }
    }

    /**
     * 将对象包装成代理对象并进行返回
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxyService(Class<T> serviceClass) {
        ClassLoader classLoader = serviceClass.getClassLoader();
        Class<?>[] interfaces = new Class[]{serviceClass};
        Integer sessionId = SessionIdGenerator.nextId();
        // 使用代理包装方法
        Object instance = Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            // 1. 将方法调用转换为消息对象 Integer sessionId, String interfaceName, String methodName, Class<?> returnType, Class<?>[] parameterType, Object[] parameterValue
            RpcRequestMessage message = new RpcRequestMessage(
                    sessionId,
                    serviceClass.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args
                    );
            // 2. 将对象发送出去
            getChannel().writeAndFlush(message);

            // 3. 准备 Promise 对象用来接收返回结果
            Promise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
            RpcResponseMessageHandler.PROMISES.put(sessionId, promise);

            // 4. 阻塞等待数据返回
            promise.await();

            // 5. 数据返回
            if (promise.isSuccess()) {
                return promise.getNow();
            } else {
                throw new RuntimeException(promise.cause());
            }
        });
        return (T)instance;
    }

    /**
     * 通过 dcl(double-check-lock) 方式获取 channel 通道(多线程下获取单例)
     * @return channel 通道
     *  1. 判断临界资源是否已经创建完成, 如果创建完成, 直接返回;
     *  2. 如果临界资源没有被创建完成, 加锁
     *      2.1 获取锁成功, 进入临界资源区域, 判断是否有其他对象已经完成了对象创建;
     *          2.1.1 创建成功, 返回单例对象;
     *          2.1.2 没有创建成功, 创建单例对象并返回;
     *      2.2 获取锁失败, 进入阻塞状态, 等待锁的持有者释放掉后在获取锁资源;
     */
    public static Channel getChannel() {
        if (channel != null) return channel;
        synchronized (LOCK) {
            if (channel != null) return channel;
            initChannel();
            return channel;
        }
    }

    /**
     * 初始化并启动 RPC 客户端, 连接地址: hostName: localhost, hostPort: 8001
     */
    private static void initChannel() {
        // 处理器
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_HANDLER = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_CLIENT_HANDLER = new RpcResponseMessageHandler();
        // 处理工作的线程
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 创建客户端启动器
        Bootstrap client = new Bootstrap();
        // 绑定 Channel 通道类型
        client.channel(NioSocketChannel.class);
        // 绑定处理事件的工作线程
        client.group(group);
        client.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                // 帧定长编/解码器
                ch.pipeline().addLast(new ProtocolFrameDecoder());
                // 日志记录
                ch.pipeline().addLast("LOGGING_HANDLER", LOGGING_HANDLER);
                // 消息编/解码器
                ch.pipeline().addLast("MESSAGE_HANDLER", MESSAGE_HANDLER);
                // RPC 响应控制器
                ch.pipeline().addLast("RPC_CLIENT_HANDLER", RPC_CLIENT_HANDLER);
            }
        });
        // 启动 RPC 客户端
        try {
            channel = client.connect(HOST_NAME, HOST_PORT).sync().channel();
            channel.closeFuture().addListener((listen) -> {
                group.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
