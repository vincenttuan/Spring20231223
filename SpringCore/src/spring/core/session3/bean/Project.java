package spring.core.session3.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope("prototype")
public class Project {
	
	@Autowired
	@Qualifier("employee2") // 指定要使用哪一個 bean name
	private Employee employee;
	
	@Autowired
	//@Qualifier("job")
	private Job job;
	
}
