package com.dang.note.proxy.websocket.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author PengBin
 * @date 2016年6月24日 下午6:04:39
 */
public class LogWebSocketHandler extends TextWebSocketHandler {

    private SimpMessagingTemplate template;

    public LogWebSocketHandler(SimpMessagingTemplate template) {
        this.template = template;
        System.out.println("初始化 handler");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String text = message.getPayload(); // 获取提交过来的消息
        System.out.println("handMessage:" + text);
        // template.convertAndSend("/topic/getLog", text); // 这里用于广播
        session.sendMessage(message);
    }
}