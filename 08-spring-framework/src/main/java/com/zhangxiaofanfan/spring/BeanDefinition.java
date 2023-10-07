package com.zhangxiaofanfan.spring;

import com.zhangxiaofanfan.spring.annotation.ScopePolicy;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午4:24
 * @description bean定义类, 可以将扫描到的bean对象信息存储下来
 */
public class BeanDefinition {
    private Class<?> clazz;     // bean 实例的 class 对象
    private ScopePolicy scope;  // bean 实例的作用域返回

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ScopePolicy getScope() {
        return scope;
    }

    public void setScope(ScopePolicy scope) {
        this.scope = scope;
    }

    public BeanDefinition() {
    }

    public BeanDefinition(Class<?> clazz, ScopePolicy scope) {
        this.clazz = clazz;
        this.scope = scope;
    }
}
