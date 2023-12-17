package com.zhangxiaofanfan.juclearn;

/**
 * 学习栈与栈帧使用的测试类
 *
 * @date 2023-12-17 23:00:11
 * @author zhangxiaofanfan
 */
public class ThreadFrame {
    public static void main(String[] args) {
        new Thread(() -> methodA(20), "thread-1").start();
        methodA(10);
    }

    private static void methodA(int x) {
        int y = x + 1;
        Object m = methodB();
        System.out.println(m);
    }

    private static Object methodB() {
        Object n = new Object();
        return n;
    }
}
