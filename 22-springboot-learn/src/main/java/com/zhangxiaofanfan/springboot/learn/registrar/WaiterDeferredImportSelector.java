package com.zhangxiaofanfan.springboot.learn.registrar;

import com.zhangxiaofanfan.springboot.learn.bean.Waiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 19:53:06
 */
@Slf4j
public class WaiterDeferredImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        log.info("WaiterDeferredImportSelector running......");
        return new String[] { Waiter.class.getName() };
    }
}
