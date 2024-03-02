package spring.core.session5.sta;

// 靜態代理
// 用來執行所代理的"業務"邏輯與實現"公用"邏輯
public class PersonProxy implements Person {
	
	// 代理對象
	private Person person;
	
	public PersonProxy(Person person) { // 傳入代理對象
		this.person = person;
	}
	
	@Override
	public void work() {
		// "公用"邏輯
		System.out.println("出門戴口罩");
		
		// "業務"邏輯
		person.work();
		
		// "公用"邏輯
		System.out.println("回家脫口罩");
	}
	
}
