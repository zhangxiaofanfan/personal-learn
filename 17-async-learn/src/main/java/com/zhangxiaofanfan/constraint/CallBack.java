package com.zhangxiaofanfan.constraint;

/**
 * 声明回调接口
 *
 * @date 2023-10-28 01:44:04
 * @author zhangxiaofanfan
 */
public interface CallBack {
    /**
     * 回调需要执行的操作
     *
     * @param obj           回调需要执行操作的对象
     * @param exception     当出现异常时将异常封装在次对象中
     */
    void doOperation(Object obj, Exception exception);
}
