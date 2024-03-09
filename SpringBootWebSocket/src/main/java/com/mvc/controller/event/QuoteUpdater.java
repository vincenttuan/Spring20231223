package com.mvc.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mvc.model.Quote;

@Component
public class QuoteUpdater {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	// 價格變動
	public void updatePrice(String symbol, String date, String open, String high, String low, String close, String volume) {
		// 建立報價物件
		Quote quote = new Quote(symbol, date, open, high, low, close, volume);
		// 建立事件
		QuoteUpdateEvent event = new QuoteUpdateEvent(this, quote);
		// 發佈事件
		eventPublisher.publishEvent(event);
	}
}
