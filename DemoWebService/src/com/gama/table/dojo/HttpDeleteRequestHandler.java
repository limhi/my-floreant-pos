package com.gama.table.dojo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.gama.table.dataobject.EventMeter;
import com.gama.table.service.EventMeterService;

public class HttpDeleteRequestHandler implements HttpRequestHandler {
 
   private EventMeterService eventMeterService;
 
   
 
   public void handleRequest(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
 
	   String id=request.getParameter("id");
	   eventMeterService.deleteById(Integer.parseInt(id));
   }



public EventMeterService getEventMeterService() {
	return eventMeterService;
}



public void setEventMeterService(EventMeterService eventMeterService) {
	this.eventMeterService = eventMeterService;
}
   
   
   
}