package com.zhangxiaofanfan.rpc.message;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 上午9:38
 * @description TODO
 */
public abstract class Message implements Serializable {
    /**
     * SessionId: 会话id, 用于识别会话使用
     * messageType: 消息类型, 通过消息类型id, 获取对应消息 Class 类型
     */
    private Integer sessionId;
    private Integer messageType;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public abstract Integer getMessageType();

    public static final Integer PRC_MESSAGE_REQUEST = 101;
    public static final Integer PRC_MESSAGE_RESPONSE = 102;

    /**
     * ConcurrentHashMap 通过 cas 机制实现线程安全, HashMap 对象是线程不安全的对象
     */
    private static final ConcurrentHashMap<Integer, Class<? extends Message>> messageClasses = new ConcurrentHashMap<>();

    static {
        messageClasses.put(PRC_MESSAGE_REQUEST, RpcRequestMessage.class);
        messageClasses.put(PRC_MESSAGE_RESPONSE, RpcResponseMessage.class);
    }

    /**
     * 通过消息类型编号返回对应的消息 class 对象
     * @param messageType Class 对象对应的编号
     * @return 返回对应编号的 Class 对象
     */
    public static Class<? extends Message> getMessageClass(Integer messageType) {
        return messageClasses.get(messageType);
    }


    /* 以下区域为 Message 对象构造器 */
    public Message() {
    }

    public Message(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Message(Integer sessionId, Integer messageType) {
        this.sessionId = sessionId;
        this.messageType = messageType;
    }
}
