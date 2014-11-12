/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.model;

import java.io.Serializable;


public class Patient extends AbstractEntity implements Serializable {

	private String id;
	private String name;
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Patient(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
