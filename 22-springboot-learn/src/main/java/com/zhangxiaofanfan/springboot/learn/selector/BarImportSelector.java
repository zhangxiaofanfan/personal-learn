package com.zhangxiaofanfan.springboot.learn.selector;

import com.zhangxiaofanfan.springboot.learn.bean.Bar;
import com.zhangxiaofanfan.springboot.learn.config.BarConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用 selector 方式: 向容器中注册 吧台 组件
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 19:20:24
 */
@Slf4j
public class BarImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        log.info("BarImportSelector running......");
        return new String[] {Bar.class.getName(), BarConfiguration.class.getName()};
    }
}
