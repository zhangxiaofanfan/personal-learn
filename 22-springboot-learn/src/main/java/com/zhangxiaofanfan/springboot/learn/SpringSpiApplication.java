package com.zhangxiaofanfan.springboot.learn;

import com.zhangxiaofanfan.springboot.learn.bean.spi.DemoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * SPI: Service Provider Interface
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 23:35:58
 */
@Slf4j
public class SpringSpiApplication {
    public static void main(String[] args) {
        List<DemoDao> demoDaoList = SpringFactoriesLoader
                .loadFactories(DemoDao.class, SpringSpiApplication.class.getClassLoader());
        demoDaoList.forEach(demoDao -> log.info("demoDao is {}", demoDao));

        log.info("---------------- 分割线 ----------------");

        List<String> demoDaoNameList = SpringFactoriesLoader
                .loadFactoryNames(DemoDao.class, SpringSpiApplication.class.getClassLoader());
        demoDaoNameList.forEach(demoDaoName -> log.info("demoDaoName is {}", demoDaoName));
    }
}

