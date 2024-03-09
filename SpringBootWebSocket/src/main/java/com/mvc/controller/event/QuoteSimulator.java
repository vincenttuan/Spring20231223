package com.mvc.controller.event;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvc.model.Quote;
import com.mvc.service.QuoteService;

import jakarta.annotation.PostConstruct;

// 此程式主要是模擬股票價格的變動
@Component
public class QuoteSimulator {
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuoteUpdater quoteUpdater;
	
	@PostConstruct
	public void simulatorPriceUpdates() {
		// 預設可以開通那些股票讓客戶端可以監聽
		//submit("^TWII");   // 可以透過 /topic/^TWII 來監聽到大盤加權股價資料
		submit("2330.TW"); // 可以透過 /topic/2330.TW 來監聽到大盤加權股價資料
		submit("1101.TW"); // 可以透過 /topic/1101.TW 來監聽到大盤加權股價資料
		submit("2303.TW"); // 可以透過 /topic/2303.TW 來監聽到大盤加權股價資料
		submit("3008.TW"); // 可以透過 /topic/3008.TW 來監聽到大盤加權股價資料
	}
	
	public void submit(String symbol) {
		Thread.startVirtualThread(() -> {
			
			while(true) {
				try {
					// 取得最新報價
					Quote quote = quoteService.getQuote(symbol);
					// 更新報價
					quoteUpdater.updatePrice(quote);
					// 模擬延遲
					Thread.sleep(new Random().nextInt(3000));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		});
	}
	
}
