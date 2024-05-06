package com.zhangxiaofanfan.cloud.framework.tracer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BizTracer配置类
 *
 * @author 麻薯
 */
@Data
@ConfigurationProperties("zxff.tracer")
public class TracerProperties {
}
