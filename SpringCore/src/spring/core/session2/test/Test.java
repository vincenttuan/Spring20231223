package spring.core.session2.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session2.bean.Author;
import spring.core.session2.bean.Book;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config2.xml");
		Author author = ctx.getBean("author", Author.class);
		System.out.println(author);
		Book book = ctx.getBean("book", Book.class);
		//book.setName("小雞吃米圖");
		System.out.println(book);
		
	}
}
