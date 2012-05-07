package com.gama.table.ws;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.gama.table.dataobject.EventMeter;
import com.gama.table.jaxb.ObjectFactory;
import com.gama.table.jaxb.QueryEventRequest;
import com.gama.table.jaxb.QueryEventResponse;
import com.gama.table.service.EventMeterService;

@Endpoint
public class QueryEventEndpoint {

  private static final String NAMESPACE_URI = "http://www.gama.com/TableWS";



  EventMeterService eventMeterService;

	 @Autowired
	 @Qualifier("fooJmsTemplate")
private JmsTemplate jmsTemplate;
  
  @Autowired
  public QueryEventEndpoint(EventMeterService eventMeterService)
      throws JDOMException {
	  this.eventMeterService=eventMeterService;

  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "QueryEventRequest")
  @ResponsePayload
  public QueryEventResponse handleHolidayRequest(@RequestPayload QueryEventRequest request)
      throws Exception {
	  System.out.println(request.getEventId());
	  System.out.println(request.getMNum());
	  System.out.println(request.getStartDate());
	  System.out.println(request.getEndDate());
	   QueryEventResponse response=new ObjectFactory().createQueryEventResponse();
	   Date startDate=null;
	   Date endDate=null;
	   if(request.getStartDate()!=null){
		   startDate=request.getStartDate().toGregorianCalendar().getTime();   
	   }
	   if(request.getEndDate()!=null){
		   endDate=request.getEndDate().toGregorianCalendar().getTime();   
	   }
	   
	   List<EventMeter> eventMeters=eventMeterService.find(request.getMNum(),request.getEventId(),startDate, endDate);
	   for (EventMeter eventMeter : eventMeters) {
		   QueryEventResponse.Events event=new QueryEventResponse.Events();
		   event.setBillIn(eventMeter.getBillIn());
		   event.setEventId(eventMeter.getEventId());
		   GregorianCalendar c = new GregorianCalendar();
			c.setTime(eventMeter.getEventTime());
			XMLGregorianCalendar date2=null;
			try {
				date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
				 event.setEventTime(date2);
			} catch (DatatypeConfigurationException e) {		
				e.printStackTrace();
			}	
			
		  
		   event.setMNum(eventMeter.getMNum());
		   event.setNote1(eventMeter.getNote1());
		   event.setNote2(eventMeter.getNote2());
		   event.setNote3(eventMeter.getNote3());
		   event.setNote4(eventMeter.getNote4());
		   response.getEvents().add(event);
	   }

    return response;
    
  }

}