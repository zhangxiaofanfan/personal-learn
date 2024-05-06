package com.zhangxiaofanfan.learn.chatroom.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 使用 BIO 实现的聊天室客户端
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 09:30:20
 */
public class ChatRoomDemoClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8080);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world1").getBytes());
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8080);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world2").getBytes());
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
