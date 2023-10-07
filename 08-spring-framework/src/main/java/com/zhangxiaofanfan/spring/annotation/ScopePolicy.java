package com.zhangxiaofanfan.spring.annotation;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午4:21
 * @description 作用域实例范围
 *      SINGLETON: 单例
 *      PROTOTYPE: 原型实例
 */
public enum ScopePolicy {
    /** SINGLETON: singleton instance */
    SINGLETON,


    /** PROTOTYPE: multiple instance */
    PROTOTYPE,
}
