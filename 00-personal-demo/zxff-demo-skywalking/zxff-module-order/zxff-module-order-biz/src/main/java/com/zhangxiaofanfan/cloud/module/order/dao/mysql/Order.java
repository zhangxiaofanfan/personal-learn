package com.zhangxiaofanfan.cloud.module.order.dao.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 15:18:13
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long productId;
    private Long userId;
    private Date createTime;
}
