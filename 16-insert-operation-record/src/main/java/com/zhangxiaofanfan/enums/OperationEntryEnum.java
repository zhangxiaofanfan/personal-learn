package com.zhangxiaofanfan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2023-10-23 09:52:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum OperationEntryEnum {
    USER(1, "用户", Arrays.asList(
            OperationEnum.CREATE,
            OperationEnum.QUERY,
            OperationEnum.UPDATE,
            OperationEnum.DELETE
    )),
    MONEY(2, "金额", Arrays.asList(
            OperationEnum.CREATE,
            OperationEnum.QUERY,
            OperationEnum.UPDATE,
            OperationEnum.DELETE
    )),
    ;

    int id;
    String name;
    List<OperationEnum> operations;
}
