package com.zhangxiaofanfan;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-08 上午11:04
 * @description TODO
 */
public interface StaticInterface {

    String name = "张帆";

    default void defaultMethod() {
        System.out.println("defaultMethod...");
    }

    static void staticMethod() {
        System.out.println("staticMethod...");
    }
}
