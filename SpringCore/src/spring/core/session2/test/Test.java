package spring.core.session2.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session2.bean.Author;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config2.xml");
		Author author = ctx.getBean("author", Author.class);
		System.out.println(author);
	}
}
