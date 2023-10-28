package com.zhangxiaofanfan.service;

import com.zhangxiaofanfan.constraint.CallBack;

import java.util.concurrent.Future;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2023-10-27 09:53:02
 */
public interface IndexService {
    Future<String> index1();

    String index2();

    Future<String> index3(CallBack callBack);
}
