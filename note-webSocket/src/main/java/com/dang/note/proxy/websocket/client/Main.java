package com.dang.note.proxy.websocket.client;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Main {
    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://http://";
            Session session = container.connectToServer(Client.class, new URI(uri));
            char lf = 10; // 这个是换行
            char nl = 0; // 这个是消息结尾的标记，一定要
            StringBuilder sb = new StringBuilder();
            sb.append("SEND").append(lf); // 请求的命令策略
            sb.append("destination:/app/hello").append(lf); // 请求的资源
            sb.append("content-length:14").append(lf).append(lf); // 消息体的长度
            sb.append("{\"name\":\"123\"}").append(nl); // 消息体

            // session.getBasicRemote().sendText(sb.toString()); // 发送消息
            Thread.sleep(500000); // 等待一小会
            session.close(); // 关闭连接

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
