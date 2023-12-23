package spring.core.session1.bean;

import java.util.Random;

import lombok.Data;

@Data
public class Lotto {
	private Integer number;
	
	public Lotto() {
		System.out.println("執行 Lotto() 建構子");
		number = new Random().nextInt(1000);
	}
	
}
