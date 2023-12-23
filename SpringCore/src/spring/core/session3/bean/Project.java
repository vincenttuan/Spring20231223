package spring.core.session3.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Project {
	
	private Employee employee;
	
	private Job job;
	
}
