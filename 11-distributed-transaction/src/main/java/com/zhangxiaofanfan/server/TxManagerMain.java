package com.zhangxiaofanfan.server;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-31 上午11:10
 * @description Tx 表示事务, 是根据 Transaction 发音而来
 */
public class TxManagerMain {
    static Log LOG = LogFactory.get();

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start("localhost", 8080);

        LOG.debug("netty 服务器启动成功......");
    }
}
