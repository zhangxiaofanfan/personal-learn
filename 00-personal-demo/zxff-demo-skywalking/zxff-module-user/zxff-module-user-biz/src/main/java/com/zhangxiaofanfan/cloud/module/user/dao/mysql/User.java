package com.zhangxiaofanfan.cloud.module.user.dao.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:37:09
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    @Schema(description = "用户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "zhangxiaofanfan")
    private String userName;
    @Schema(description = "用户年龄", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    private Integer userAge;
    @Schema(description = "用户性别, 0 表示女, 1 表示男", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer userSex;
}
