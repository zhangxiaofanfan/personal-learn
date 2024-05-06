package com.zhangxiaofanfan.cloud.module.user.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:23:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "查询到的用户信息")
public class UserDTO {
    @Schema(description = "用户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "zhangxiaofanfan")
    private String userName;
    @Schema(description = "用户年龄", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    private Integer userAge;
    @Schema(description = "用户性别, 0 表示女, 1 表示男", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer userSex;
}
