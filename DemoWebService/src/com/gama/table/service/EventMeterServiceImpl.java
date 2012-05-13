package com.gama.table.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.gama.table.dao.EventMeterDAO;
import com.gama.table.dataobject.EventMeter;

public class EventMeterServiceImpl implements EventMeterService{
	EventMeterDAO eventMeterDAO;
	public EventMeterDAO getEventMeterDAO() {
		return eventMeterDAO;
	}
	public void setEventMeterDAO(EventMeterDAO eventMeterDAO) {
		this.eventMeterDAO = eventMeterDAO;
	}
	@Override
	public void insertEvent(EventMeter event) {
		eventMeterDAO.makePersistent(event);
		
	}
	@Override
	public List<EventMeter> findAll() {
		return eventMeterDAO.findAll();
	}
	@Override
	public List<EventMeter> find(int MNum, int eventId, Date stateDate,
			Date endDate) {
		
		DetachedCriteria crit=eventMeterDAO.createDetachedCriteria();
		if(MNum!=0){
			crit.add(Restrictions.eq("MNum", MNum));
		}
		if(eventId!=0){
			crit.add(Restrictions.eq("eventId", eventId));
		}
		if(stateDate!=null){
			crit.add(Restrictions.ge("eventTime", stateDate));
		}
		if(endDate!=null){
			crit.add(Restrictions.le("eventTime", endDate));
		}
		return eventMeterDAO.findByCriteria(crit);
	}
	@Override
	public EventMeter findById(Integer id) {
		return eventMeterDAO.findById(id);
	}
	@Override
	public void deleteById(Integer id) {
		eventMeterDAO.makeTransient(eventMeterDAO.findById(id));
		
	}
	@Override
	public EventMeter saveOrUpdate(EventMeter entity) {
		
		return eventMeterDAO.makePersistent(entity);
	}
	

}
