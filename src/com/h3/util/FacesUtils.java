/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class FacesUtils {

    public static void sendInfoMessageToUser(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
        addMessageToJsfContext(facesMessage);
    }

    public static void sendErrorMessageToUser(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR, message);
        addMessageToJsfContext(facesMessage);
    }

    private static FacesMessage createMessage(Severity severity, String mensagemErr) {
        return new FacesMessage(severity, mensagemErr, mensagemErr);
    }

    private static void addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static UIComponent findComponent(String id) {
        return FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
    }

    public static void resetForm(String formId) {
        FacesContext.getCurrentInstance().getViewRoot().findComponent(formId).getChildren().clear();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    // add the errors message in the screen and write into log 
    public static void handleError(Exception e, Logger log) {
        String stackTrace = SysUtil.getStackTrace(e);
        log.error(stackTrace);
        sendErrorMessageToUser(stackTrace);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
}
