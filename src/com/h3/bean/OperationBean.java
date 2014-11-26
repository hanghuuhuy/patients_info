package com.h3.bean;

/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import com.h3.db.OperationDAO;
import com.h3.model.Operation;

@ViewScoped
@ManagedBean
public class OperationBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// log
	private static final Logger logger = Logger.getLogger(OperationBean.class);

	private Operation operation;

	private List<Operation> operations;

	private OperationDAO operationDAO;

	private Date filterDate;

	private boolean filterIsShow;

	private Operation selectedOperation;

	@PostConstruct
	public void init() {
		logger.info("init");

		this.operationDAO = new OperationDAO();

		operation = new Operation();

		filterDate = new Date();

		loadDataFromDatabase();
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Operation getSelectedOperation() {
		return this.selectedOperation;
	}

	public void setSelectedOperation(Operation selectedOperation) {
		this.selectedOperation = selectedOperation;
	}

	public Date getFilterDate() {
		return this.filterDate;
	}

	public void setFilterDate(Date filterDate) {
		this.filterDate = filterDate;
	}

	public boolean getFilterIsShow() {
		return this.filterIsShow;
	}

	public void setFilterIsShow(boolean filterIsShow) {
		this.filterIsShow = filterIsShow;
	}

	// ------------------------

	private void loadDataFromDatabase() {
		if (this.filterIsShow) {
			doDisplayListOfIsShow();
		} else {
			doDisplayListOfDate();
		}
	}

	// ------------------------

	public void save() {
		logger.info("save");

		if (this.operation.getId() >= 0) {
			this.operationDAO.update(operation);
		} else {
			this.operationDAO.add(operation);
		}
		
		operation = new Operation();

		loadDataFromDatabase();
	}

	public void doDisplayListOfDate() {
		this.filterIsShow = false;

		operations = this.operationDAO.getAllOfDate(this.filterDate);
	}

	public void doDisplayListOfIsShow() {
		this.filterIsShow = true;

		operations = this.operationDAO.getAllOfIsShow();
	}

	public void doEditItem() {
		if (this.selectedOperation != null) {
			this.operation = this.selectedOperation;
		}
	}
	
	public boolean checkIsEditMode() {
		if (this.operation != null) {
			if (this.operation.getId() > 0) {
				return true;
			}
		}
			
		return false;
	}
}
