package com.zhangxiaofanfan.rpc.message;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 上午9:59
 * @description 封装了 RPC 请求需要的信息
 *      主要包含的是反射调用时所需要的信息
 */
public class RpcRequestMessage extends Message {

    /**
     * 调用接口的全限定类型, 服务器根据这个名称找到实现
     */
    private String interfaceName;
    /**
     * 调用接口的方法名称
     */
    private String methodName;
    /**
     * 返回值类型
     */
    private Class<?> returnType;
    /**
     * 方法参数类型
     */
    private Class<?>[] parameterType;
    /**
     * 方法
     */
    private Object[] parameterValue;

    /**
     * 重写父类抽象方法, 返回对象编号
     * @return RpcMessageRequest 类型编号为 101
     */
    @Override
    public Integer getMessageType() {
        return Message.PRC_MESSAGE_REQUEST;
    }

    public RpcRequestMessage(Integer sessionId, String interfaceName, String methodName, Class<?> returnType, Class<?>[] parameterType, Object[] parameterValue) {
        super(sessionId);
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public Class<?>[] getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?>[] parameterType) {
        this.parameterType = parameterType;
    }

    public Object[] getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Object[] parameterValue) {
        this.parameterValue = parameterValue;
    }
}
