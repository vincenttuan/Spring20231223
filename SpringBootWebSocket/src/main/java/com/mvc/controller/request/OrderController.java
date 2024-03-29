package com.mvc.controller.request;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mvc.model.CommissionReturn;
import com.mvc.model.Order;
import com.mvc.model.TransactionReturn;

@Controller
public class OrderController {
	private static AtomicInteger sysOrderId = new AtomicInteger(0); // 委託單編號
	private static AtomicInteger sysTxId = new AtomicInteger(0); // 成交單編號
	
	// 用來發送訊息的模板
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	// 下委託單
	@MessageMapping("/order-cr") // WS-Client 透過 /app/order-cr 發送訊息
	public void orderCR(Order order, Principal principal) throws Exception {
		// principal 裡面包含 spring-security 的登入帳戶資訊
		System.out.println("委託單: " + order);
		order.setUsername(principal.getName());
		// 建立委託單物件
		String orderId = sysOrderId.incrementAndGet() + "";
		String orderTime = LocalDateTime.now().toString();
		CommissionReturn commissionReturn = new CommissionReturn(orderId, "委託成功", orderTime, order);
		// 設定委託回報給指定使用者
		//messagingTemplate.convertAndSend("/topic/commission-return", commissionReturn); // 公開回報
		messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/commission-return", commissionReturn);
		
		// 模擬交易搓合
		new Thread(() -> {
			try {
				Thread.sleep(new Random().nextInt(5000));
				// 建立成交單物件
				String txId = sysTxId.incrementAndGet() + "";
				String txTime = LocalDateTime.now().toString();
				Double txPrice = order.getPrice();
				Integer txAmount = order.getAmount();
				TransactionReturn transactionReturn = new TransactionReturn(txId, "全部成交", txTime, txPrice, txAmount, commissionReturn);
				// 設定成交回報給指定使用者
				messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/transaction-return", transactionReturn);
				
			} catch (Exception e) {
				
			}
		}).start();
		
	}
	
}
