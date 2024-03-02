package spring.core.session5.sta;

public class CalcProxy implements Calc {
	
	private Calc calc;
	
	public CalcProxy(Calc calc) {
		this.calc = calc;
	}
	
	@Override
	public Integer calculate(Integer x, Integer y) {
		// 前置通知
		// 檢查 x = null?
		if(x == null || y == null) {
			String errorMessage = "";
			if(x == null) {
				errorMessage = "例外通知: x 不可是 null";
			}
			if(y == null) {
				errorMessage += errorMessage.isEmpty() ? "" : ", ";
				errorMessage += "例外通知: y 不可是 null";
			}
			throw new IllegalArgumentException(errorMessage); // 拋出一個參數錯誤例外
		}
		
		// 檢查若 calc 是 Div 時分母不可 = 0
		if(calc instanceof Div && y == 0) {
			throw new ArithmeticException("例外通知: 分母不可 = 0");
		}
		
		return calc.calculate(x, y);
	}

}
