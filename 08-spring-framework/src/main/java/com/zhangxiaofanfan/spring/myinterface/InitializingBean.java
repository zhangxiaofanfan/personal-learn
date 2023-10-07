package com.zhangxiaofanfan.spring.myinterface;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午2:43
 * @description 初始化 Bean
 */
public interface InitializingBean {
    /**
     * 属性设置完回调函数
     */
    void afterPropertiesSet();
}
