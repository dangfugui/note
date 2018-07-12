package com.dang.note.springboot.notespringboot.websocket;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.DeploymentException;
import javax.websocket.Session;

@ClientEndpoint
public class CaptionWebSocketTest {

    public static void main(String[] args)
            throws DeploymentException, IOException, URISyntaxException, InterruptedException {
        WebSocketClient client = new WebSocketClient();
        Session session = client.client("ws://127.0.0.1:8080/websocket");
        session.getAsyncRemote().sendText("你好");
        Thread.sleep(1000000);

    }

}
