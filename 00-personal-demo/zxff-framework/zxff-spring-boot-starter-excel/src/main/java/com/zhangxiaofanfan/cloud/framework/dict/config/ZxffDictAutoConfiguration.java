package com.zhangxiaofanfan.cloud.framework.dict.config;

import com.zhangxiaofanfan.cloud.framework.dict.core.DictFrameworkUtils;
import com.zhangxiaofanfan.cloud.module.system.api.dict.DictDataApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ZxffDictAutoConfiguration {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public DictFrameworkUtils dictUtils(DictDataApi dictDataApi) {
        DictFrameworkUtils.init(dictDataApi);
        return new DictFrameworkUtils();
    }

}
