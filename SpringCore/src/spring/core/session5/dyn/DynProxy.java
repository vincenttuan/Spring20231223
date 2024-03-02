package spring.core.session5.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
			
			return null;
		};
		//newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) 
		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
		return proxyObj;
	}
	
}
