package com.mvc.controller.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mvc.model.Greeting;
import com.mvc.model.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class RegisterController {
	
	// 用來發送訊息的模板
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/register")
	// Message 收到的資料結構
	// Greeting 回傳的資料結構
	public void register(Message message) throws Exception {
		String username = message.content();
		Boolean status = username.equals("john");
		String content = status ? "註冊成功" : "註冊失敗";
		Greeting greeting = new Greeting(status, content);
		// 因為 server 會透過 "/topic/greetings" 通道傳送訊息
		// 所以 client 端也必須訂閱 "/topic/greetings" 通道接收訊息
		simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
	}
	
}
