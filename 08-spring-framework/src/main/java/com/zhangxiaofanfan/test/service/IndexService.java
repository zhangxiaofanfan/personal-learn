package com.zhangxiaofanfan.test.service;

import com.zhangxiaofanfan.spring.annotation.Component;
import com.zhangxiaofanfan.spring.myinterface.BeanPostProcessor;
import com.zhangxiaofanfan.spring.myinterface.InitializingBean;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 上午11:33
 * @description TODO
 */
@Component
public class IndexService implements InitializingBean, BeanPostProcessor {
    @Override
    public void afterPropertiesSet() {
        System.out.println("IndexService 初始化 设置完成后...");
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前...");
        return null;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后...");
        return null;
    }
}
