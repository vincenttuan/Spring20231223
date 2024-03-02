package spring.core.session5.dyn;

import java.lang.reflect.Method;
import java.util.Arrays;

// 用來集中所有的公用邏輯
// Aspect 切面程式
public class MyAspect {
	// 公用方法: 前置通知(Advice)
	public static void before(Method method, Object[] args) {
		System.out.printf("進行前置通知程序... 方法: %s 方法參數: %s%n", method.getName(), Arrays.toString(args));
	}
	// 公用方法: 例外通知(Advice)
	public static void throwing(Throwable e) {
		System.out.println("進行例外通知 ... " + e);
	}
	
	// 公用方法: 後置通知(Advice)
	public static void end() {
		System.out.println("進行後置通知程序...");
	}
}
