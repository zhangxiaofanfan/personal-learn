package com.zhangxiaofanfan.spring.myinterface;

import com.zhangxiaofanfan.spring.BeanDefinition;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午4:34
 * @description ioc 容器规范
 */
public interface ApplicationContext {
    /**
     * 根据 Bean 名称和类对象创建 Bean 实例
     * @param beanName 需要创建的 Bean 名称
     * @param beanDefinition 需要创建的 Bean 类对象
     * @return 返回创建的对象
     */
    Object createBean(String beanName, BeanDefinition beanDefinition);

    /**
     * 扫描此应用中需要被注册的组件
     * @param configClass 配置类
     */
    void scanComponent(Class<?> configClass);

    /**
     * 从容器中获取对应Bean
     * @param beanName 需要获取的 Bean 实例的名称
     * @param beanClass 需要获取的 Bean 实例的类对象
     * @param <T> 类型
     * @return 对应Bean
     */
    <T> T getBean(String beanName, Class<T> beanClass);

    /**
     * 从容器中获取对应Bean
     * @param beanName 需要获取的 Bean 实例的名称
     * @return 对应Bean
     */
    Object getBean(String beanName);
}
