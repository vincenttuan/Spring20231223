package spring.core.session6.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 被 Spring 所管理的原件
@Aspect // 切面程式
@Order(2) // 調用順序
public class MySecurityAspect {
	
	
}
