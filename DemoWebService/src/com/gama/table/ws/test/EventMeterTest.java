package com.gama.table.ws.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gama.table.dataobject.EventMeter;
import com.gama.table.service.EventMeterService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-applicationContext-config.xml"})
public class EventMeterTest {
	 @Autowired
	private EventMeterService service;


	@Test
	public void txManagerTest() {
		EventMeter eventMeter=new EventMeter();
		eventMeter.setBillIn((long)1000);
		eventMeter.setEventId(7);
		eventMeter.setEventTime(new Date());
		eventMeter.setId(999);
		eventMeter.setMNum(12345);
		service.insertEvent(eventMeter);
		
    }
//	
	
}



