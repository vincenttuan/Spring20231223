package spring.core.session5.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

// 動態代理
// 可以代理任何物件
public class DynProxy {
	
	// 被代理的物件
	private Object object;
	
	public DynProxy(Object object) {
		this.object = object;
	}
	
	// 取得代理物件
	public Object getProxy() {
		Object proxyObj = null;
		// 1. 載入類別器
		ClassLoader loader = object.getClass().getClassLoader();
		// 2. 被代理物件所實作的介面
		Class<?>[] interfaces = object.getClass().getInterfaces(); 
		// 3. 處理代理的實現
		InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
			Object result = null;
			// 公用方法: 前置通知
			MyAspect.before(method, args);
			MyLoggerAspect.before(method, args);
			// 執行業務方法
			try {
				result = method.invoke(object, args); // object: 被代理的物件, args: 方法參數
				//System.out.println(result);
			} catch (Throwable e) {
				// 公用方法: 例外通知
				MyAspect.throwing(e);
			} finally {
				// 公用方法: 後置通知
				MyAspect.end();
			}
			return result;
		};
		//newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) 
		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
		return proxyObj;
	}
	
}
