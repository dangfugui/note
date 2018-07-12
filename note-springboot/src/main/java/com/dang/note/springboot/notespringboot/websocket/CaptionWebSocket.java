package com.dang.note.springboot.notespringboot.websocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dang.note.springboot.notespringboot.core.LogUtils;

@ServerEndpoint("/websocket")
@Component
public class CaptionWebSocket {

    private static Logger log = LoggerFactory.getLogger(CaptionWebSocket.class);
    private static Queue<Session> seesionQueue = new LinkedBlockingQueue<Session>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(final Session session) throws IOException, InterruptedException {
        log.info("onOpen");
        seesionQueue.add(session);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Queue<String> queue = LogUtils.readLog("/data/logs/peixun-live-chat_0103.log.sub");
                    String json = null;
                    long sleep = 1000 / 4;

                    while ((json = queue.poll()) != null) {
                        json = "{\n"
                                + "    \"type\": 3,\n"
                                + "    \"message\": " + json + ",\n"
                                + "    \"time\": \"\",\n"
                                + "    \"userName\": \"\",\n"
                                + "    \"userRealName\": \"\",\n"
                                + "    \"history\": 0\n"
                                + "}";
                        session.getBasicRemote().sendText(json);
                        Thread.sleep(sleep);
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(final Session session) {
        log.info("onClose");
        seesionQueue.remove(session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("onMessage：" + message);
    }

    /**
     * 发生错误时调用,如果session open也可能发生异常
     *
     * @param session
     * @param e
     */
    @OnError
    public void onError(Session session, Throwable e) {
        log.error("onError:", e);
        seesionQueue.remove(session);
    }

    public static Queue<Session> getSeesionQueue() {
        return seesionQueue;
    }
}