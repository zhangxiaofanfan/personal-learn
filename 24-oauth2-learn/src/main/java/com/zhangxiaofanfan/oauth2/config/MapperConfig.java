package com.zhangxiaofanfan.oauth2.config;

import org.mybatis.spring.annotation.MapperScan;

/**
 * 配置 mybatis mapper 扫描包名
 *
 * @author zhangxiaofanfan
 * @date 2024-03-02 11:28:40
 */
@MapperScan("com.zhangxiaofanfan.oauth2.mapper.*")
public class MapperConfig {
}
