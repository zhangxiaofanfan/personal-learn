package com.zhangxiaofanfan.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 上午10:07
 * @description 定义需要扫描的包, 用户只可以添加一个基础包;
 */
@Retention(RetentionPolicy.RUNTIME) // 在程序运行时生效
@Target(ElementType.TYPE)
public @interface ComponentScan {

    String value();
}
