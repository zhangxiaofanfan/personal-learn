package com.zhangxiaofanfan.springboot.learn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

/**
 * 调酒师的模型类
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 14:43:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bartender {
    private String name;
}
