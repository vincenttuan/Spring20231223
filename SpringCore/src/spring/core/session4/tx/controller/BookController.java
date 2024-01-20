package spring.core.session4.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.core.session4.tx.service.BookOneService;

@Controller
public class BookController {
	
	@Autowired
	private BookOneService bookOneService;
	
	// 買單本書
	public void buyOneBook(String username, Integer bookId) {
		bookOneService.buyOne(username, bookId);
		System.out.println("buyOneBook OK !");
	}
	
	// 買3本書
	public void buyThreeBooks(String username, Integer bookId) {
		bookOneService.buyOne(username, bookId);
		bookOneService.buyOne(username, bookId);
		bookOneService.buyOne(username, bookId);
		System.out.println("buyThreeBooks OK !");
	}
	
}
