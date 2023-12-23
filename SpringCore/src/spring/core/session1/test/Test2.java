package spring.core.session1.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session1.bean.User;

public class Test2 {

	public static void main(String[] args) {
		// 指定 xml 設定檔
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml"); 
//		User user = ctx.getBean("user", User.class); // IOC
//		System.out.println(user);
//		User user2 = ctx.getBean("user2", User.class);
//		System.out.println(user2);
//		User user3 = ctx.getBean("user3", User.class);
//		System.out.println(user3);
//		User user4 = ctx.getBean("user4", User.class);
//		System.out.println(user4);
	}

}
