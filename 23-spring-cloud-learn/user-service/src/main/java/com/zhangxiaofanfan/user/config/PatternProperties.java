package com.zhangxiaofanfan.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置属性, 学习nacos使用类
 *
 * @author zhangxiaofanfan
 * @date 2024-01-23 10:15:07
 */
@Data
@Component
@ConfigurationProperties("pattern.data")
public class PatternProperties {
    private String format;
}
