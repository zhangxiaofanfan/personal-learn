package com.zhangxiaofanfan.learn.chatroom.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用 BIO 实现的聊天室服务端
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 09:30:20
 */
public class ChatRoomDemoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        // 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // 阻塞方法获取链接
                    Socket socket = serverSocket.accept();
                    System.out.println("socket: " + socket);
                    // 为每个新连接都创建一个新线程, 负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // 按照字节流的方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
