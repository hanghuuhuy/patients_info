package com.h3.bean;

/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.h3.model.Patient;
import com.h3.util.DBUtils;

@ViewScoped
@ManagedBean
public class DashboardBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 // log
    private static final Logger logger = Logger.getLogger(DashboardBean.class);

    private List patients;
    
    @PostConstruct
    public void init() {

        logger.info("init");

        refresh();
    }
    
	public void refresh() {
		logger.info("refresh");

		// reload database		// patients = DBUtils.query("selec top 2 from patient where isShowed = true order by lastUpdatedTime", Patient.class);

		patients = new ArrayList<Patient>();

		patients.add(new Patient("ID".concat(String.valueOf(new Random().nextInt())), "Nguyễn Văn ".concat(String.valueOf(new Random().nextInt()))));
		patients.add(new Patient("ID".concat(String.valueOf(new Random().nextInt())), "Le Văn ".concat(String.valueOf(new Random().nextInt()))));
	}
    
	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
    
}
