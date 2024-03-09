package com.mvc.controller.request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.mvc.model.ServerTime;

@Controller
@EnableScheduling
public class ScheduleController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	// 建立一個虛擬執行緒的服務
	private ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
	
	// 使用虛擬執行緒每一秒發送一次數據
	@Scheduled(fixedDelay = 1000)
	public void sendTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		virtualThreadExecutor.submit(() -> {
			// 將 ServerTime 當成回傳資訊
			ServerTime serverTime = new ServerTime(sdf.format(new Date()));
			simpMessagingTemplate.convertAndSend("/topic/servertime", serverTime);
		});
	}
	
	
}
