package com.dang.note.proxy.websocket.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("注册");
        registry.addEndpoint("/hello").withSockJS(); // 注册端点，和普通服务端的/log一样的
        // withSockJS()表示支持socktJS访问，在浏览器中使用
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        System.out.println("启动");
        config.enableSimpleBroker("/topic"); //
        config.setApplicationDestinationPrefixes("/app"); // 格式前缀
    }

}