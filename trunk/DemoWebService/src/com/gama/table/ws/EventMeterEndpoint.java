package com.gama.table.ws;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.gama.table.dataobject.EventMeter;
import com.gama.table.jaxb.EventMeterRequest;
import com.gama.table.jaxb.EventMeterResult;
import com.gama.table.jaxb.ObjectFactory;
import com.gama.table.service.EventMeterService;
 
@Endpoint
public class EventMeterEndpoint {

  private static final String NAMESPACE_URI = "http://www.gama.com/TableWS";



  EventMeterService eventMeterService;

	 @Autowired
	 @Qualifier("fooJmsTemplate")
private JmsTemplate jmsTemplate;
  
  @Autowired
  public EventMeterEndpoint(EventMeterService eventMeterService)
      throws JDOMException {
	  this.eventMeterService=eventMeterService;


  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EventMeterRequest")
  @ResponsePayload
  public EventMeterResult handleHolidayRequest(@RequestPayload EventMeterRequest request)
      throws Exception {
	  
	  EventMeter meter=new EventMeter();
	  meter.setMNum(request.getMNum());
	  meter.setEventId(request.getEventId());
	  meter.setBillIn((long)request.getBillIn());
	  meter.setNote1(request.getNote1());
	  meter.setNote2(request.getNote2());
	  meter.setNote3(request.getNote3());
	  meter.setNote4(request.getNote4());
	 
	  meter.setEventTime(request.getEventTime().toGregorianCalendar().getTime());
	   eventMeterService.insertEvent(meter);
	   final String msg="Hello!I'm server, your eventId is "+meter.getEventId();
	   if(meter.getEventId()!=79){
		   jmsTemplate.send( 
		           new MessageCreator() {
		                public Message createMessage(Session session) 
		                throws JMSException {
		                     return session.createTextMessage(msg);
		         }
		           }
		     );
	   }
	  EventMeterResult response=new ObjectFactory().createEventMeterResult();
	  response.setSuccess(true);
//	  throw new Exception("error");
    return response;
    
  }

}