/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.bean;

import java.io.Serializable;

import com.h3.util.FacesUtils;
import com.h3.util.ResourcesLoader;

public class AbstractBean implements Serializable {

    protected void displayErrorMessageToUser(String message) {
        FacesUtils.sendErrorMessageToUser(message);
    }

    protected void displayInfoMessageToUser(String message) {
        FacesUtils.sendInfoMessageToUser(message);
    }

    protected void displayErrorMessageIdToUser(String messageId) {
        FacesUtils.sendErrorMessageToUser(ResourcesLoader.getString(messageId));
    }

    protected void displayInfoMessageIdToUser(String messageId) {
        FacesUtils.sendInfoMessageToUser(ResourcesLoader.getString(messageId));
    }


}
