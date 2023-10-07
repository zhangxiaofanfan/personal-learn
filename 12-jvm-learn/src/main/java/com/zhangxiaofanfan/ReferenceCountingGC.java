package com.zhangxiaofanfan;

/**
 * 测试 JVM 判断对象消亡是否使用的是引用计数方法
 *
 * @author zhangxiaofanfan
 */
public class ReferenceCountingGC {

    private Object instance = null;
    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
