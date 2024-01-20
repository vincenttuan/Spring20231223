package spring.core.session4.tx.service;

import spring.core.session4.tx.exception.InsufficientAmount;
import spring.core.session4.tx.exception.InsufficientStock;

public interface BookOneService {
	void buyOne(String username, Integer bookId) throws InsufficientStock, InsufficientAmount; // 購書服務
	void refundOne(String username, Integer bookId); // 退書服務
}
