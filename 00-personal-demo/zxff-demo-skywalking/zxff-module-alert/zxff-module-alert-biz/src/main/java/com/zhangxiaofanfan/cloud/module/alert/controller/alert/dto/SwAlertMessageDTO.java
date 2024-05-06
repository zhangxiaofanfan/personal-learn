package com.zhangxiaofanfan.cloud.module.alert.controller.alert.dto;

import lombok.Data;
import lombok.ToString;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-06 10:08:48
 */
@Data
@ToString
public class SwAlertMessageDTO {
    private int scopeId;
    private String name;
    private String id0;
    private String id1;
    private String alertMessage;
    private Long startTime;
    private String ruleName;
}
