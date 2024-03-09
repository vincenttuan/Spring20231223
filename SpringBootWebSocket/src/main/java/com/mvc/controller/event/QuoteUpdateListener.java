package com.mvc.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.mvc.model.Quote;

// 監聽 Quote update 的資訊
@Component
public class QuoteUpdateListener {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@EventListener
	// event 所放的就是最新報價資料
	public void onStockPriceUpdate(QuoteUpdateEvent event) {
		Quote quote = event.getQuote();
		messagingTemplate.convertAndSend("/topic/" + quote.symbol(), quote);
	}
	
}
