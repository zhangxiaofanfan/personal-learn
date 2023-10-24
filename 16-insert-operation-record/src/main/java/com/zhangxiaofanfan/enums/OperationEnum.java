package com.zhangxiaofanfan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 操作记录
 *
 * @author zhangxiaofanfan
 * @date 2023-10-18 17:18:41
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OperationEnum {
    CREATE("新增记录"),
    QUERY("查找记录"),
    UPDATE("更新记录"),
    DELETE("删除记录"),
    ;
    private String desc;
}
