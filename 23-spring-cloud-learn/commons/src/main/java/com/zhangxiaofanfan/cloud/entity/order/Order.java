package com.zhangxiaofanfan.cloud.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhangxiaofanfan.cloud.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    @TableField(exist = false)
    private User user;
}