package spring.core.session2.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data // Lombok
@Component // Spring
public class Book {
	@Value("1") // Spring 
	private Integer id;
	@Value("春樹秋霜圖")
	private String name;
	@Value("100")
	private Integer price;
}
