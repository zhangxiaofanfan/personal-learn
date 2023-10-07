package com.zhangxiaofanfan.spring.myinterface;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午2:41
 * @description Bean 后置处理器
 */
public interface BeanPostProcessor {
    /**
     * 初始化前执行的方法
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName);

    /**
     * 初始后前执行的方法
     */
    Object postProcessorAfterInitialization(Object bean, String beanName);
}
