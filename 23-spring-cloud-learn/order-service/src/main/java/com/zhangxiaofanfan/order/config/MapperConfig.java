package com.zhangxiaofanfan.order.config;

import com.baomidou.mybatisplus.autoconfigure.DdlApplicationRunner;
import com.baomidou.mybatisplus.extension.ddl.IDdl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-01-18 12:19:49
 */
@Configuration
@MapperScan("com.zhangxiaofanfan.order.mapper")
public class MapperConfig {
    @Autowired(required = false)
    private List<IDdl> ddlList;

    @Bean
    public DdlApplicationRunner ddlApplicationRunner() {
        return new DdlApplicationRunner(ddlList);
    }
}
