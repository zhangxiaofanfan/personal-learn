package com.zhangxiaofanfan.springboot.learn.annotation;

import com.zhangxiaofanfan.springboot.learn.bean.Boss;
import com.zhangxiaofanfan.springboot.learn.config.BartenderConfiguration;
import com.zhangxiaofanfan.springboot.learn.registrar.WaiterDeferredImportSelector;
import com.zhangxiaofanfan.springboot.learn.registrar.WaiterRegistrar;
import com.zhangxiaofanfan.springboot.learn.selector.BarImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启酒馆功能的注解
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 12:25:26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
        Boss.class,
        BartenderConfiguration.class,
        BarImportSelector.class,
        WaiterDeferredImportSelector.class,
        WaiterRegistrar.class,
})
public @interface EnableTavern {
}
