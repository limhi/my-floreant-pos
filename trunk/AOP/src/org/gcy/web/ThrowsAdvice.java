package org.gcy.web;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * source : www.javabeat.net
 */
public class ThrowsAdvice {
	private Logger log = Logger.getLogger(this.getClass());
	public void doThrowing(JoinPoint jp, Throwable ex) {
		System.out.println("錯誤發生在此");
		System.out.println("method " + jp.getTarget().getClass().getName()
				+ "." + jp.getSignature().getName() + " throw exception");
		ex.printStackTrace(System.out);

	}


	public static void main(String[] arvg) {
		ApplicationContext context;
		context = new FileSystemXmlApplicationContext("classpath:spring-aop.cfg.xml");
		
		ExceptionClassA a=(ExceptionClassA)context.getBean("ExceptionA");
//		ExceptionClassB b=(ExceptionClassB)context.getBean("ExceptionB");
		try{
			a.oldExceptionHandler();
//			b.oldExceptionHandler()
		}catch (Exception e) {
			System.out.println("畫面提示有錯誤產生,個別處理Exception");
		}
		
		
		
		try{
			a.newExceptionHandler();
//			b.newExceptionHandler();
		}catch (Exception e) {
			System.out.println("畫面提示有錯誤產生,但是錯誤處理已經被ThrowsAdvice統一做掉了");
		}
		

		
	}
	

}