package spring.core.session4.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.core.session4.tx.dao.BookDao;

@Repository
public class BookOneServiceImpl implements BookOneService {
	final Integer ONE = 1;
	@Autowired
	private BookDao bookDao;
	
	@Override
	public void buyOne(String username, Integer bookId) { // 買一本書
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去庫存
		bookDao.reduceBookStock(bookId, ONE);
		// 3. 修改餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	}

	@Override
	public void refundOne(String username, Integer bookId) { // 退一本書
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 回存庫存
		bookDao.incrementBookStock(bookId, ONE);
		// 3. 修改餘額
		bookDao.incrementWalletBalance(username, bookPrice);
	}

}
