package com.zhangxiaofanfan.rpc.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-30 下午3:41
 * @description 定长帧解码器
 */
public class ProtocolFrameDecoder extends LengthFieldBasedFrameDecoder {

    /**
     * 定长帧解码器: 解决半包/粘包现象
     *      maxFrameLength: 单个请求最多接收的长度
     *      lengthFieldOffset: 表示数据包长度的偏移量
     *      lengthFieldLength: 长度所占用的字节数
     */
    public ProtocolFrameDecoder() {
        this(1024, 12, 4);
    }

    private ProtocolFrameDecoder(int maxFrameLength,
                                 int lengthFieldOffset,
                                 int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

}
