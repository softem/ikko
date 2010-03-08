package jp.co.softem.ikko.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@TimeLog
@Interceptor
public class TimeLogInterceptor {

	@AroundInvoke
	public Object invoke(InvocationContext ic) {

		Object result;
		try {
			long start = System.nanoTime();
			result = ic.proceed();
			long end = System.nanoTime();
			System.out.println(String.format("時間%,d ns", end - start));
			return result;
		} catch (Exception ex) {
			Logger.getLogger(TimeLogInterceptor.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		return null;
	}
}
