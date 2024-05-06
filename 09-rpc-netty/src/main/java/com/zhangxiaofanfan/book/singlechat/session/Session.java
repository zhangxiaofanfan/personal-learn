package com.zhangxiaofanfan.book.singlechat.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 记录用户会话信息
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 22:05:20
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;
}
