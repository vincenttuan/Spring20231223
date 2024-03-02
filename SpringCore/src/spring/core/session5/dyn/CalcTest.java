package spring.core.session5.dyn;

import spring.core.session5.sta.Man;
import spring.core.session5.sta.Person;

public class CalcTest {

	public static void main(String[] args) {
		// 利用動態代理
		DynProxy dynProxy = new DynProxy(new CalcImpl()); // 建立動態代理
		Calc calc = (Calc)dynProxy.getProxy(); // 取得代理物件
		System.out.println(calc.add(20, 10));
		System.out.println(calc.div(20, 10));
		
		Person man = (Person)new DynProxy(new Man()).getProxy();
		man.work();
	}

}
