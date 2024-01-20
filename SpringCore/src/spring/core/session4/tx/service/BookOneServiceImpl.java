package spring.core.session4.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.core.session4.tx.dao.BookDao;

@Service
public class BookOneServiceImpl implements BookOneService {
	final Integer ONE = 1;
	@Autowired
	private BookDao bookDao;
	
	@Override
	// Spring 默認 Error, RuntimeException 也會進行回滾
	@Transactional(
			propagation = Propagation.REQUIRED // 預設: 若當前有 tx, 則繼續使用, 若無則建立一個 tx
	)
	public void buyOne(String username, Integer bookId) { // 買一本書
		// 紀錄 log
		log();
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去庫存
		bookDao.reduceBookStock(bookId, ONE);
		// 3. 修改餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	} // commit

	@Override
	public void refundOne(String username, Integer bookId) { // 退一本書
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 回存庫存
		bookDao.incrementBookStock(bookId, ONE);
		// 3. 修改餘額
		bookDao.incrementWalletBalance(username, bookPrice);
	}
	
	// log 紀錄
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void log() {
		// 資料庫存入一筆 log
	}
	
}
