package org.gcy.web;

import org.apache.log4j.Logger;

public class ExceptionClassA {
	Logger log=Logger.getLogger(this.getClass());
	
	public void oldExceptionHandler() throws Exception{

		try{
			throw new Exception("出現了Exception A");
		}catch (Exception e) {
			e.printStackTrace(System.out);			
			throw e;
		}		
	}
	
	public void newExceptionHandler() throws Exception{

		throw new Exception("出現了Exception A");
	}
	
}
