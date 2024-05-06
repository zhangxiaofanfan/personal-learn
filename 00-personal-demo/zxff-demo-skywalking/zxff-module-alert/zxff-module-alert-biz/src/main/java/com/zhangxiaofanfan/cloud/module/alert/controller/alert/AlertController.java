package com.zhangxiaofanfan.cloud.module.alert.controller.alert;

import com.alibaba.fastjson.JSON;
import com.zhangxiaofanfan.cloud.module.alert.controller.alert.dto.SwAlertMessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-06 10:02:05
 */
@Slf4j
@Tag(name = " 报警模块")
@RestController
@RequestMapping("/alerting")
public class AlertController {

    @Operation(summary = "sw 报警回调接口")
    @PostMapping("/notify")
    public void alertingNotify(@RequestBody List<SwAlertMessageDTO> message) {
        log.info("[告警通知] 告警消息: {}", JSON.toJSONString(message));
    }
}