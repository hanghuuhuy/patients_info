package com.h3.bean;

/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.h3.db.OperationDAO;
import com.h3.model.Operation;

@ViewScoped
@ManagedBean(name = "showBean")
public class ShowBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	// log
	private static final Logger logger = Logger.getLogger(ShowBean.class);

	private Operation operation1;
	private Operation operation2;

	private List<Operation> operations;

	private OperationDAO operationDAO;

	private int currentPosition;

	@PostConstruct
	public void init() {
		logger.info("init");

		this.operationDAO = new OperationDAO();

		this.currentPosition = 0;

		loadData();
	}

	public Operation getOperation1() {
		return operation1;
	}

	public void setOperation1(Operation operation) {
		this.operation1 = operation;
	}

	public Operation getOperation2() {
		return operation2;
	}

	public void setOperation2(Operation operation) {
		this.operation2 = operation;
	}

	// ------------------------

	public void loadData() {
		if (operations == null) {
			operations = this.operationDAO.getAllOfIsShow();
		}

		if (operations != null) {

			if (operations.size() > 2) {
				this.currentPosition++;

				if (this.currentPosition >= operations.size()) {
					operations = this.operationDAO.getAllOfIsShow();
					this.currentPosition = 0;
				}
			} else {
				operations = this.operationDAO.getAllOfIsShow();
				this.currentPosition = 0;
			}

			if (operations.size() > 1) {

				this.operation1 = operations.get(currentPosition);

				if (this.currentPosition == operations.size() - 1) {
					operation2 = operations.get(0);
				} else {
					operation2 = operations.get(this.currentPosition + 1);
				}

			} else if (operations.size() > 0) {
				this.operation1 = operations.get(currentPosition);
				this.operation2 = null;
			} else {
				this.operation1 = null;
				this.operation2 = null;
			}

		}

	}

}
