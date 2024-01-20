package spring.core.session4.tx.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session4.tx.controller.BookController;

public class BookTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-tx-config.xml");
		
		BookController bookController = ctx.getBean(BookController.class);
		// John 買一本書 book_id = 1 的書
		bookController.buyOneBook("John", 1);
		
		
	}
}
