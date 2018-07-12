package com.dang.note.springboot.notespringboot.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientEndpoint
public class WebSocketClient {
    private static Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

    private Session session;
    private String uri;

    public Session client(String uri) throws URISyntaxException, IOException, DeploymentException {
        this.uri = uri;
        // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket
        // .WsWebSocketContainer");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.setDefaultMaxSessionIdleTimeout(6 * 60 * 60 * 1000);
        container.setAsyncSendTimeout(6 * 60 * 60 * 1000);
        session = container.connectToServer(this, new URI(uri)); // 连接会话
        session.setMaxIdleTimeout(6 * 60 * 60 * 1000);

        // session.getBasicRemote().sendText("123132132131"); // 发送文本消息
        return session;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        logger.info("onOpen: " + session.getBasicRemote());
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("onMessage: " + message);
    }

    @OnError
    public void onError(Throwable t) {
        logger.error("onError", t);
    }

    @OnClose
    public void onClose() {
        logger.info("onClose: " + session.getBasicRemote());
    }
}
