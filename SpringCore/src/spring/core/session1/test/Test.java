package spring.core.session1.test;

import java.util.Random;

import spring.core.session1.bean.User;

public class Test {

	public static void main(String[] args) {
		User user = new User();
		user.setId(1);
		user.setName("John");
		user.setScore(new Random().nextInt(101));
		
		System.out.println(user);
	}

}
