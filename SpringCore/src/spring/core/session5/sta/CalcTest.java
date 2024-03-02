package spring.core.session5.sta;

public class CalcTest {

	public static void main(String[] args) {
		// 在 Add.java 與 Div.java 不可以變動的情況下
		// 如何利用 CalcProxy 可以處理除法中分母 = 0 的問題
		// ex: 例外通知: 分母不可 = 0
		// 如何利用 CalcProxy 可以處理 x 或 y = null 的問題
		// ex: 例外通知: x 不可是 null, y 不可是 null
		// 測試:
		// add.calculate(20, 10) --> 得到 30
		// div.calculate(20, 10) --> 得到 2
		// add.calculate(null, 10) --> 例外通知: x 不可是 null
		// add.calculate(null, null) --> 例外通知: x 不可是 null, 例外通知: y 不可是 null
		// div.calculate(20, 0) --> 例外通知: 分母不可 = 0
		
	}

}
