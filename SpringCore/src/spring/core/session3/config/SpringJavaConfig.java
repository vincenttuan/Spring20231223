package spring.core.session3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.core.session3.bean.Employee;
import spring.core.session3.bean.Title;

@Configuration // Spring Java 配置檔
@ComponentScan(basePackages = "spring.core.session3")
public class SpringJavaConfig {
	
	@Bean
	public Title title1() {
		Title title = new Title();
		title.setId(1);
		title.setName("初階工程師");
		title.setSalary(45000);
		return title;
	}
	
	@Bean
	public Title title2() {
		Title title = new Title();
		title.setId(2);
		title.setName("PM");
		title.setSalary(100_000);
		return title;
	}
	
	@Bean(name = "emp1")
	public Employee employee1() {
		Employee employee = new Employee();
		employee.setId(101);
		employee.setName("John");
		employee.setTitle(title1());
		return employee;
	}
	
	@Bean
	public Employee employee2() {
		Employee employee = new Employee();
		employee.setId(202);
		employee.setName("Mary");
		employee.setTitle(title2());
		return employee;
	}
	
}
