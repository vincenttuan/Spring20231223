package com.mvc.psi.read;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ReadEmployee {
	
	@Transactional
	@Test
	public void readOne() {
		// 請協助找出 "張三" 所屬的部門總共有哪些幾位員工 ?
	}
	
}
