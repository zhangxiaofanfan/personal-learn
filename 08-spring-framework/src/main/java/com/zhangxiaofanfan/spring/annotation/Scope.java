package com.zhangxiaofanfan.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午4:20
 * @description 作用实例范围
 */
@Retention(RetentionPolicy.RUNTIME) // 在程序运行时生效
@Target(ElementType.TYPE)
public @interface Scope {
    // 默认是单例
    ScopePolicy value() default ScopePolicy.SINGLETON;
}
