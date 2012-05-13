package com.gama.table.dojo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.gama.table.dataobject.EventMeter;
import com.gama.table.service.EventMeterService;

public class HttpUpdateRequestHandler implements HttpRequestHandler {
 
   private EventMeterService eventMeterService;
 
   
 
   public void handleRequest(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
 
       
	   String id=request.getParameter("id");
	   if(id==null || "".equals(id)){
		   EventMeter entity= new EventMeter();
		   entity.setBillIn(((long) 0));
		   entity.setNote1(0);
		   entity.setNote2(0);
		   entity.setNote3(0);
		   entity.setNote4(0);
		   entity.setMNum(0);
		   entity.setEventId(0);
		   entity.setEventTime(new Date());
		   
		   eventMeterService.insertEvent(entity);
	   }else{
		   EventMeter entity=eventMeterService.findById(Integer.parseInt(id));
		   String type=request.getParameter("type");
		   String value=request.getParameter("value");
		
		   try {
			   EventMeter.class.getField(type);
			   Method method=EventMeter.class.getMethod("set"+type.substring(0,1).toUpperCase()+type.substring(1,type.length()), EventMeter.class.getField(type).getType()
					   );
			  
			   Class<?> c=method.getParameterTypes()[0];
			   Object object=null;
			   if(c==Integer.class){
				   object=Integer.parseInt(value);
			   }else if(c==Long.class){
				   object=Long.parseLong(value);
			   }else if(c==Date.class){
				   object=new Date(Long.parseLong(value));
			   }
			   
			   method.invoke(entity, object);
			   eventMeterService.saveOrUpdate(entity);
		} catch (SecurityException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
       
   }



public EventMeterService getEventMeterService() {
	return eventMeterService;
}



public void setEventMeterService(EventMeterService eventMeterService) {
	this.eventMeterService = eventMeterService;
}
   
   
   
}