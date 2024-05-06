package com.zhangxiaofanfan.oauth2.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-02 11:26:24
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class SysUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private Boolean enabled;
}
