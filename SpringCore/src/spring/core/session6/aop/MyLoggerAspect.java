package spring.core.session6.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 被 Spring 所管理的原件
@Aspect // 切面程式
@Order(1) // 調用順序
public class MyLoggerAspect {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// 建立一個切入點的邏輯方法
	@Pointcut(value = "execution(public Integer spring.core.session6.aop.CalcImpl.add(Integer, Integer))")
	public void ptAdd() {}
	
	@Pointcut(value = "execution(public Integer spring.core.session6.aop.CalcImpl.sub(Integer, Integer))")
	public void ptSub() {}
	
	@Pointcut(value = "execution(public Integer spring.core.session6.aop.CalcImpl.mul(Integer, Integer))")
	public void ptMul() {}
	
	@Pointcut(value = "execution(public Integer spring.core.session6.aop.CalcImpl.div(Integer, Integer))")
	public void ptDiv() {}
	
	@Pointcut(value = "execution(public Integer spring.core.session6.aop.CalcImpl.*(..))")
	public void ptAll() {}
	
	@Before(value = "execution(public Integer spring.core.session6.aop.CalcImpl.add(Integer, Integer))")
	//@Before(value = "execution(public Integer spring.core.session6.aop.CalcImpl.*(Integer, Integer))")
	//@Before(value = "execution(public Integer spring.core.session6.aop.CalcImpl.*(..))")
	//@Before(value = "execution(* spring.core.session6.aop.CalcImpl.*(..))")
	//@Before(value = "execution(* spring.core.session6.aop.*.*(..))")
	//@Before(value = "execution(* *(..))")
	//@Before(value = "ptAdd()")
	//@Before(value = "ptAdd() && ptMul()")
	//@Before(value = "ptAll() && !ptDiv()") // 切入點表達式支援邏輯運算子: &&, ||, !
	public void beforeAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		Object[] args = joinPoint.getArgs(); // 方法參數
		String dateTime = sdf.format(new Date());
		// log 紀錄
		System.out.printf("Log 前置通知: %s %s %s%n", dateTime, methodName, args);
	}
	
}
