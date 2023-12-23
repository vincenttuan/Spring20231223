package spring.core.session1.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session1.bean.Lotto;
import spring.core.session1.bean.User;

public class Test3 {

	public static void main(String[] args) {
		// 指定 xml 設定檔
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml"); 
//		Lotto lotto1 = ctx.getBean("lotto", Lotto.class);
//		Lotto lotto2 = ctx.getBean("lotto", Lotto.class);
//		System.out.println(lotto1);
//		System.out.println(lotto2);
	}

}
