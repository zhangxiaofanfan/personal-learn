package com.zhangxiaofanfan.springboot.learn.registrar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用 registrar 方式: 向容器中注册 服务员 组件
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 19:29:42
 */
@Slf4j
public class WaiterRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.info("WaiterRegistrar running......");
        registry.registerBeanDefinition("waiter", new RootBeanDefinition(WaiterRegistrar.class));
    }
}
