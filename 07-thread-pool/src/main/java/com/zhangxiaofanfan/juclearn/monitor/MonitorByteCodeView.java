package com.zhangxiaofanfan.juclearn.monitor;

/**
 * 通过字节码学习 Monitor 使用类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-22 12:48:45
 */
public class MonitorByteCodeView {
    static final Object lock = new Object();
    static int count = 0;
    public static void main(String[] args) {
        synchronized (lock) {
            count++;
        }
    }
}
