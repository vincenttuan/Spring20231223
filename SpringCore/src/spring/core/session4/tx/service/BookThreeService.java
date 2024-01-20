package spring.core.session4.tx.service;

import spring.core.session4.tx.exception.InsufficientAmount;
import spring.core.session4.tx.exception.InsufficientStock;

public interface BookThreeService {
	void buyThree(String username, Integer bookId) throws InsufficientStock, InsufficientAmount ; // 購書服務
}
