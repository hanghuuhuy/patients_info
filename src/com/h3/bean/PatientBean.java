package com.h3.bean;

/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.h3.model.Patient;

@ViewScoped
@ManagedBean
public class PatientBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 // log
    private static final Logger logger = Logger.getLogger(PatientBean.class);

    private Patient patient;
    
    private List<Patient> patients;
    
    @PostConstruct
    public void init() {

        logger.info("init");
        
        patient = new Patient();
        
        patients = new ArrayList<Patient>();
        
        patients.add(new Patient("11", "Nguyễn Văn A"));
        patients.add(new Patient("22", "Nguyễn Văn B"));
    }
    
    public void save() {
    	
    	logger.info("save");
    	
    	patients.add(patient);
    	patient = new Patient();
    	
    }

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
    
}
