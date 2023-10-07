package com.zhangxiaofanfan.postprocessor;

import com.zhangxiaofanfan.pojo.Hello;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 将 BeanProcessor 注册到容器中
 *
 * @author zhangxiaofanfan
 * @date 2023-07-25 09:24:49
 */
public class HelloPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Hello) {
            System.out.println("Hello postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Hello) {
            System.out.println("Hello postProcessAfterInitialization");
        }
        return bean;
    }
}
