package com.zhangxiaofanfan.juclearn.designpatterns;

/**
 * 单例设计模式
 *
 * @author zhangxiaofanfan
 * @date 2024-01-16 12:47:55
 */
public final class Singleton {
    private static Singleton INSTANCE = null;
    private Singleton() {}

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
