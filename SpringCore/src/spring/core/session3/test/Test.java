package spring.core.session3.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.core.session3.bean.Employee;
import spring.core.session3.bean.Job;
import spring.core.session3.config.SpringJavaConfig;

// 啟動參數: --add-opens java.base/java.lang=ALL-UNNAMED
public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfig.class);
		Employee employee1 = ctx.getBean("emp1", Employee.class);
		System.out.println(employee1);
		Employee employee2 = ctx.getBean("employee2", Employee.class);
		System.out.println(employee2);
		
		Job work = ctx.getBean("work", Job.class);
		System.out.println(work);
		
		Job job = ctx.getBean("job", Job.class);
		System.out.println(job);
		
	}
}
