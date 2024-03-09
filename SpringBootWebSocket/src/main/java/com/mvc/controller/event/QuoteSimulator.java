package com.mvc.controller.event;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.model.Quote;
import com.mvc.service.QuoteService;

// 此程式主要是模擬股票價格的變動
@Component
public class QuoteSimulator {
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuoteUpdater quoteUpdater;
	
	
	
	public void submit(String symbol) {
		Thread.startVirtualThread(() -> {
			
			while(true) {
				try {
					Quote quote = quoteService.getQuote(symbol);
					// 更新報價
					quoteUpdater.updatePrice(quote);
					// 模擬延遲
					Thread.sleep(new Random().nextInt(3000));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}).start();
	}
	
}
