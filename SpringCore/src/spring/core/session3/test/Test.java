package spring.core.session3.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.core.session3.config.SpringJavaConfig;

// 啟動參數: --add-opens java.base/java.lang=ALL-UNNAMED
public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfig.class);
		
		
	}
}
