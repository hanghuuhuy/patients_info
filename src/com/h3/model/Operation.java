/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

import com.h3.util.MyUtil;

public class Operation extends AbstractEntity implements Serializable {

	private int id;
	private String patientId;
	private String patientName;
	private int patientAge;
	private String operatingRoom;
	private String status;
	private String note;
	private Timestamp startTime;
	private Timestamp endTime;
	private String mainSurgeon;
	private boolean isShow;

	public Operation() {
		this.id = -1;
		this.startTime = new Timestamp(System.currentTimeMillis());
		this.endTime = new Timestamp(System.currentTimeMillis());
	}

	public Operation(int id, String patientId, String patientName, int patientAge, String operatingRoom, String status, String note, Timestamp startTime, Timestamp endTime, String mainSurgeon, boolean isShow) {
		this.id = id;
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.operatingRoom = operatingRoom;
		this.status = status;
		this.note = note;
		this.startTime = startTime;
		this.endTime = endTime;
		this.mainSurgeon = mainSurgeon;
		this.isShow = isShow;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientAge() {
		return this.patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getOperatingRoom() {
		return this.operatingRoom;
	}

	public void setOperatingRoom(String operatingRoom) {
		this.operatingRoom = operatingRoom;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Date getUtilStartTime() {
		return new Date(this.startTime.getTime());
	}

	public void setUtilStartTime(Date startTime) {
		this.startTime.setTime(startTime.getTime());
	}

	public String getFormattedStartTime() {
		return MyUtil.getFormattedDateTime(getUtilStartTime());
	}
	
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Date getUtilEndTime() {
		return new Date(this.endTime.getTime());
	}

	public void setUtilEndTime(Date endTime) {
		this.endTime.setTime(endTime.getTime());
	}
	
	public String getFormattedEndTime() {
		return MyUtil.getFormattedDateTime(getUtilEndTime());
	}

	public String getMainSurgeon() {
		return this.mainSurgeon;
	}

	public void setMainSurgeon(String mainSurgeon) {
		this.mainSurgeon = mainSurgeon;
	}

	public boolean getIsShow() {
		return this.isShow;
	}

	public void setIsShow(boolean isShow) {
		this.isShow = isShow;
	}

}
