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
	
	// 消息代理配置
	// configureMessageBroker方法配置了一個消息代理，以便在客戶端通過訂閱的方式接收服務器推送過來的消息。
	// 這裡配置了一個簡單的消息代理，以/topic為前綴來標識訂閱的消息。
	// 這樣客戶端只需要訂閱 "/topic/xxx" 這樣的地址就可以接收服務器推送過來的消息。
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/queue");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	
	
}
