package spring.core.session2.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data // Lombok
@Component // Spring <bean>
public class Author {
	@Value("9527")
	private Integer id;
	
	@Value("華安")
	private String name;
}
