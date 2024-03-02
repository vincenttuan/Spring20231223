package spring.core.session6.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 被 Spring 所管理的原件
@Aspect // 切面程式
@Order(1) // 調用順序
public class MyLoggerAspect {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Before(value = "execution(public Integer spring.core.session6.dyn.CalcImpl.add(Integer, Integer))")
	public void beforeAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		Object[] args = joinPoint.getArgs(); // 方法參數
		String dateTime = sdf.format(new Date());
		// log 紀錄
		System.out.printf("Log 前置通知: %s %s %s%n", dateTime, methodName, args);
	}
	
}
