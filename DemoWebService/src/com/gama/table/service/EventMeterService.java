package com.gama.table.service;

import java.util.Date;
import java.util.List;

import com.gama.table.dataobject.EventMeter;

public interface EventMeterService {
	public void insertEvent(EventMeter event);
	
	public List<EventMeter> findAll();
	
	public List<EventMeter> find(int MNum,int eventId,Date stateDate,Date endDate);
	
}
