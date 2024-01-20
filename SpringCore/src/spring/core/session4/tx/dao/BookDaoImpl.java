package spring.core.session4.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer getBookPrice(Integer bookId) { // 取得書本價格
		String sql = "select book_price from book where book_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
	}

	@Override
	public Integer getBookStock(Integer bookId) { // 取得書本庫存
		String sql = "select book_amount from stock where book_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
	}

	@Override
	public Integer getWalletBalance(String username) { // 取得客戶目前的餘額
		String sql = "select balance from wallet where username = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, username);
	}

	@Override
	public Integer reduceBookStock(Integer bookId, Integer amountToReduce) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer reduceWalletBalance(String username, Integer amountToReduce) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer incrementBookStock(Integer bookId, Integer amountToIncrement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer incrementWalletBalance(String username, Integer amountToIncrement) {
		// TODO Auto-generated method stub
		return null;
	}

}
