package com.mvc.controller.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class RegisterController {
	
	// 用來發送訊息的模板
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/register")
	public void register(Message message) throws Exception {
		String username = message
	}
	
}
