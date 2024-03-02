package spring.core.session6.aop;

import org.springframework.stereotype.Component;

@Component // 預設 @Component("calcImpl") 其中 calcImpl 就是 bean name
public class CalcImpl implements Calc {

	@Override
	public Integer add(Integer x, Integer y) {
		return x + y;
	}

	@Override
	public Integer sub(Integer x, Integer y) {
		return x - y;
	}

	@Override
	public Integer mul(Integer x, Integer y) {
		return x * y;
	}

	@Override
	public Integer div(Integer x, Integer y) {
		return x / y;
	}

}
