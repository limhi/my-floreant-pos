package com.gama.table.dojo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.gama.table.dataobject.EventMeter;
import com.gama.table.service.EventMeterService;

public class HttpLoadRequestHandler implements HttpRequestHandler {
 
   private EventMeterService eventMeterService;
 
   
 
   public void handleRequest(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
 
       
       System.out.println(eventMeterService.findAll().toString());
       List<EventMeter> list=eventMeterService.findAll();
       
       String data=PojoMapper.toJson(list, true);
//		String data=PojoMapper.toJson(newMap, true);
		
		
		 httpServletResponse.getOutputStream().print(data);
		
//       for (EventMeter eventMeter : list) {
//    	   httpServletResponse.getOutputStream().print(eventMeter.getId()+"\n");
//	}
   }



public EventMeterService getEventMeterService() {
	return eventMeterService;
}



public void setEventMeterService(EventMeterService eventMeterService) {
	this.eventMeterService = eventMeterService;
}
   
   
   
}