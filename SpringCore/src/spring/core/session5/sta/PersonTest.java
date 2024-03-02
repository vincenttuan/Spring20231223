package spring.core.session5.sta;

public class PersonTest {

	public static void main(String[] args) {
		// 不使用代理
		/*
		Person man = new Man();
		Person woman = new Woman();
		man.work();
		woman.work();
		*/
		// 使用代理
		Person man = new PersonProxy(new Man());
		Person woman = new PersonProxy(new Woman());
		man.work();
		woman.work();
		
		
	}

}
