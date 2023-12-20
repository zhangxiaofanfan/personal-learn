package com.zhangxiaofanfan.juclearn;

/**
 * 判断包装类相等 demo
 *
 * @author zhangxiaofanfan
 * @date 2023-12-20 23:39:27
 */
public class ThreadIntegerEquals {
    public static void main(String[] args) {
        Integer num1 = 127;
        Integer num2 = 127;
        Integer num3 = 128;
        Integer num4 = 128;
        System.out.println(num1 == num2);
        System.out.println(num3 == num4);
    }
}
