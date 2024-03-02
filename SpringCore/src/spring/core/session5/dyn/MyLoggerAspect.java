package spring.core.session5.dyn;

import java.lang.reflect.Method;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

// 紀錄所有使用者下達參數的切面程式
public class MyLoggerAspect {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 前置通知
	public static void before(Method method, Object[] args) {
		if(args != null) {
			String filePath = "src/spring/core/session5/dyn/log.txt";
			String content = sdf.format(new Date()) + " " + method.getName() + " " + Arrays.toString(args);
			try {
				Files.write(
						Paths.get(filePath), // 存檔路徑
						content.getBytes(StandardCharsets.UTF_8), // 轉 byte[] 陣列並編碼
						StandardOpenOption.CREATE, // 若文件不存在, 則會創建新的文件
						StandardOpenOption.APPEND); // 新的資料添加到文件末尾
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
