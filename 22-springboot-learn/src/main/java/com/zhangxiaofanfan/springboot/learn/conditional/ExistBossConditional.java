package com.zhangxiaofanfan.springboot.learn.conditional;

import com.zhangxiaofanfan.springboot.learn.bean.Boss;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * 创建 condition 实现, 当满足要求时, 向容器中注入标注了 @Conditional 注解的对象
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 23:16:35
 */
public class ExistBossConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return Objects.requireNonNull(context.getBeanFactory()).containsBeanDefinition(Boss.class.getName());
    }
}
