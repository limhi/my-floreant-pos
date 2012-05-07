package com.gama.table.generics;

import java.io.Serializable;


/**
 *@author Gush
 * @param <ID>
 */
public abstract class GenericDataObject<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1183347722809801395L;
	/**
	 * 物件的unique key
	 */
	ID id;
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

}
