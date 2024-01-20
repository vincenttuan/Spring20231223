package spring.core.session4.tx.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session4.tx.controller.BookController;

public class BookTest2 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-tx-config.xml");
		
		BookController bookController = ctx.getBean(BookController.class);
		// Mary 買三本書 book_id = 2 的書
		bookController.buyThreeBooks("Mary", 2);
		
				
	}
}
