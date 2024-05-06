package com.zhangxiaofanfan.user.config;

import com.baomidou.mybatisplus.autoconfigure.DdlApplicationRunner;
import com.baomidou.mybatisplus.extension.ddl.IDdl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 配置 mybatis 扫描的 mapper 包地址
 *
 * @author zhangxiaofanfan
 * @date 2024-01-18 09:56:04
 */
@Configuration
@MapperScan("com.zhangxiaofanfan.user.mapper")
public class MapperConfig {
    @Autowired(required = false)
    private List<IDdl> ddlList;

    @Bean
    public DdlApplicationRunner ddlApplicationRunner() {
        return new DdlApplicationRunner(ddlList);
    }
}
