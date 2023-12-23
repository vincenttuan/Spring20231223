package spring.core.session3.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope("prototype")
public class Project {
	
	private Employee employee;
	
	private Job job;
	
}
