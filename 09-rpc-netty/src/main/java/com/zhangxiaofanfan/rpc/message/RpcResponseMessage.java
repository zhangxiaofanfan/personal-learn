package com.zhangxiaofanfan.rpc.message;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 上午9:59
 * @description 封装了 Rpc 响应数据
 */
public class RpcResponseMessage extends Message {
    /**
     * 调用正常时返回值
     */
    private Object returnValue;

    /**
     * 调用异常时返回的异常信息
     */
    private Exception exceptionValue;

    /**
     * 重写父类抽象方法, 返回对象编号
     * @return RpcMessageResponse 类型编号为 102
     */
    @Override
    public Integer getMessageType() {
        return Message.PRC_MESSAGE_RESPONSE;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Exception getExceptionValue() {
        return exceptionValue;
    }

    public void setExceptionValue(Exception exceptionValue) {
        this.exceptionValue = exceptionValue;
    }
}
