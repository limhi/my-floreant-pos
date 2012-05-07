package com.floreantpos.model;

import com.floreantpos.model.base.BaseTester;

public class Tester extends BaseTester {
	private static final long serialVersionUID = 1L;

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public Tester () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Tester (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Tester (
		java.lang.Integer id,
		java.lang.String name) {

		super (
			id,
			name);
	}

	/*[CONSTRUCTOR MARKER END]*/

	@Override
	public String toString() {
		return getName();
	}
}