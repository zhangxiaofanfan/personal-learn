package com.zhangxiaofanfan.aop;

import com.zhangxiaofanfan.enums.OperationEntryEnum;
import com.zhangxiaofanfan.enums.OperationEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作记录注解
 *
 * @author zhangxiaofanfan
 * @date 2023-10-18 17:08:30
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationRecord {

    OperationEntryEnum operationType();

    OperationEnum operation();
}
