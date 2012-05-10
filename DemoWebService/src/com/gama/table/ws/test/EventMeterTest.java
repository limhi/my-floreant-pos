package com.gama.table.ws.test;

import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gama.table.dao.IssueDAO;
import com.gama.table.dao.IssueDetailDAO;
import com.gama.table.dataobject.Issue;
import com.gama.table.dataobject.IssueDetail;
import com.gama.table.service.EventMeterService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-applicationContext-config.xml"})
public class EventMeterTest {
	 @Autowired
	private EventMeterService service;

	 
	 @Autowired
	private IssueDAO issueDAO;
	
	 @Autowired
	 private IssueDetailDAO issueDetailDAO;
	 
	 
//	@Test
//	public void txManagerTest() {
//		EventMeter eventMeter=new EventMeter();
//		eventMeter.setBillIn((long)1000);
//		eventMeter.setEventId(7);
//		eventMeter.setEventTime(new Date());
//		eventMeter.setId(999);
//		eventMeter.setMNum(12345);
//		service.insertEvent(eventMeter);
//		
//    }
//	
	
	
	@Test
	public void fromParent() {
		Issue entity=new Issue();
		IssueDetail detail=new IssueDetail();
		IssueDetail detail2=new IssueDetail();
		entity.getIssueDetails().add(detail);
		entity.getIssueDetails().add(detail2);
		
		/**
		 * 沒有這行會出錯,因為IssueDetail.hbm.xml設定了not-null="true"
		 * 
		 */
		detail.setIssue(entity);
		detail2.setIssue(entity);
		/**
		 * 雖然只存了Issue,但是IssueDetail也一併存檔了,這是因為設定了cascade的關係
		 */
		issueDAO.makePersistent(entity);
    }
	
	
	@Test
	public void detachedCriteria() {
		service.find(0, 0, null	, null);
    }
	
	
	@Test
	public void lazyTest() {
		DetachedCriteria crit=issueDAO.createDetachedCriteria().add(Restrictions.eq("id",2));
		crit.setFetchMode("issueDetails", FetchMode.JOIN);
		
		Issue entity=issueDAO.findByCriteria(crit).get(0);
//		System.out.println(entity.getIssueDetails().ge==null);
		
		System.out.println(entity.getIssueDetails().toString());
    }
}



