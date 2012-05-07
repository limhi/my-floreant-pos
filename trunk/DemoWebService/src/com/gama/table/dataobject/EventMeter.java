package com.gama.table.dataobject;

import java.util.Date;

import com.gama.table.generics.GenericDataObject;

public class EventMeter extends GenericDataObject<Integer>{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1523086412454976100L;
	Integer MNum;
	Integer eventId;
	Date eventTime;
	Long billIn;
	Integer note1;
	Integer note2;
	Integer note3;
	Integer note4;
	public Integer getMNum() {
		return MNum;
	}
	public void setMNum(Integer mNum) {
		MNum = mNum;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public Long getBillIn() {
		return billIn;
	}
	public void setBillIn(Long billIn) {
		this.billIn = billIn;
	}
	public Integer getNote1() {
		return note1;
	}
	public void setNote1(Integer note1) {
		this.note1 = note1;
	}
	public Integer getNote2() {
		return note2;
	}
	public void setNote2(Integer note2) {
		this.note2 = note2;
	}
	public Integer getNote3() {
		return note3;
	}
	public void setNote3(Integer note3) {
		this.note3 = note3;
	}
	public Integer getNote4() {
		return note4;
	}
	public void setNote4(Integer note4) {
		this.note4 = note4;
	}
	
	
	
	
	
}

