package spring.core.session4.tx.dao;

import spring.core.session4.tx.exception.InsufficientAmount;
import spring.core.session4.tx.exception.InsufficientStock;

public interface BookDao {
	// 一般服務
	Integer getBookPrice(Integer bookId); // 取得書本價格
	Integer getBookStock(Integer bookId); // 取得書本庫存
	Integer getWalletBalance(String username); // 取得客戶目前的餘額
	
	// 交易服務
	Integer reduceBookStock(Integer bookId, Integer amountToReduce) throws InsufficientStock; // 更新書本庫存(減量)
	Integer reduceWalletBalance(String username, Integer amountToReduce) throws InsufficientAmount; // 更新錢包餘額(減量)
	
	Integer incrementBookStock(Integer bookId, Integer amountToIncrement);// 更新書本庫存(增量)
	Integer incrementWalletBalance(String username, Integer amountToIncrement); // 更新錢包餘額(增量)
	
}
