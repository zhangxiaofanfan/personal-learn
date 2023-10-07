package com.zhangxiaofanfan;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-08 上午11:03
 * @description TODO
 */
public class Main {
    static final int A = 10;
    static final int B;

    final int c = 20;
    final int d;

    static {
        System.out.println("静态代码块...");
        B = 15;
    }

    {
        System.out.println("代码块...");
        d = 25;
    }

    public static void main(String[] args) {
        new Main();
    }
}
