package com.gama.table.dataobject;

import java.util.HashSet;
import java.util.Set;

import com.gama.table.generics.GenericDataObject;



public class Issue extends GenericDataObject<Integer>{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956308065633309699L;
	Set<IssueDetail> issueDetails= new HashSet<IssueDetail>();
	public Set<IssueDetail> getIssueDetails() {
		return issueDetails;
	}
	public void setIssueDetails(Set<IssueDetail> issueDetails) {
		this.issueDetails = issueDetails;
	}

	
	
	
	
	
}