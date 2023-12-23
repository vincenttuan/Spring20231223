package spring.core.session1.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class User {
	private Integer id;
	private String name;
	private Integer score;
	
	public User() {
		System.out.println("執行 User() 建構子");
	}
	
	public User(Integer id, String name, Integer score) {
		System.out.println("執行 User() 建構子");
		this.id = id;
		this.name = name;
		this.score = score;
	}
	
	
}
