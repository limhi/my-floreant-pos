package com.gama.table.dataobject;

import com.gama.table.generics.GenericDataObject;




public class IssueDetail extends GenericDataObject<Integer>{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5843134852859105992L;
	private Issue issue;
	

	
	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
}