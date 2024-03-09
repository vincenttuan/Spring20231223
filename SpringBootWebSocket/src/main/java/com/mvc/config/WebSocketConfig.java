package com.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	// 端點註冊
	// registerStompEndpoints方法註冊了一個STOMP端點/spring-boot-websocket，
	// 客戶端將使用這個端點來連接WebSocket服務器。這個端點是STOMP客戶端與服務器進行交互的起點。
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/spring-boot-websocket").setAllowedOrigins("*");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
	}
	
	
	
}
