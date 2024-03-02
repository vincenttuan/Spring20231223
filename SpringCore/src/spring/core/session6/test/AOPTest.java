package spring.core.session6.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session6.aop.CalcImpl;
import spring.core.session6.aop.Calc;

//執行時要加入: JVM 啟動參數
//--add-opens java.base/java.lang=ALL-UNNAMED
//允許 Java 9 以上版本使用反射(java.lang.reflect)內部 API
public class AOPTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-aop.xml");
		Calc calc = ctx.getBean("calcImpl", CalcImpl.class);
		System.out.println(calc.add(10, 40));
		System.out.println(calc.sub(20, 30));
		System.out.println(calc.mul(30, 20));
		System.out.println(calc.div(40, 10));
		
	}

}
