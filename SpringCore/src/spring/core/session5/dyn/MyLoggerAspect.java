package spring.core.session5.dyn;

import java.lang.reflect.Method;

// 紀錄所有使用者下達參數的切面程式
public class MyLoggerAspect {
	
	// 前置通知
	public static void before(Method method, Object[] args) {
		if(args != null) {
			String filePath = "src/spring/core/session5/dyn/log.txt";
			
		}
	}
	
}
