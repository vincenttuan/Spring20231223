package spring.core.session4.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.core.session4.tx.exception.InsufficientAmount;
import spring.core.session4.tx.exception.InsufficientStock;
import spring.core.session4.tx.service.BookOneService;
import spring.core.session4.tx.service.BookThreeService;

@Controller
public class BookController {
	
	@Autowired
	private BookOneService bookOneService;
	
	@Autowired
	private BookThreeService bookThreeService;
	
	// 買單本書
	public void buyOneBook(String username, Integer bookId) {
		try {
			bookOneService.buyOne(username, bookId);
		} catch (InsufficientStock | InsufficientAmount e) {
			System.out.println("buyOneBook Error !");
			return;
		}
		System.out.println("buyOneBook OK !");
	}
	
	// 買3本書
	public void buyThreeBooks(String username, Integer bookId) {
		try {
			bookThreeService.buyThree(username, bookId);
		} catch (InsufficientStock | InsufficientAmount e) {
			System.out.println("buyThreeBooks Error !");
			return;
		}
		System.out.println("buyThreeBooks OK !");
	}
	
}
