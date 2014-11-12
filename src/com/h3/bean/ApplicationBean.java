/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;

@ApplicationScoped
@ManagedBean(name = "applicationBean", eager = true)
public class ApplicationBean implements Serializable {

    // log
    private static final Logger logger = Logger.getLogger(ApplicationBean.class);

    // load when starting server
    @PostConstruct
    public void init() {
        logger.info("applicationBean intial...");

        // load the resource here!
    }

   
}
