package com.mvc.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mvc.model.Quote;

// 主要工作: 建立事件 + 發佈價格變動的通知
@Component
public class QuoteUpdater {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	// 價格變動
	public void updatePrice(Quote quote) {
		// 建立事件
		QuoteUpdateEvent event = new QuoteUpdateEvent(this, quote);
		// 發佈事件
		eventPublisher.publishEvent(event);
	}
}
