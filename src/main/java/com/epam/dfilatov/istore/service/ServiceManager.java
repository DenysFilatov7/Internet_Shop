package com.epam.dfilatov.istore.service;

import javax.servlet.ServletContext;

public class ServiceManager {
    public static ServiceManager getInstance(ServletContext servletContext) {
        ServiceManager instance = (ServiceManager) servletContext.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(servletContext);
            servletContext.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public void close() {//close resources
    }

    private BussinessService businessService;

    public BussinessService getBusinessService() {
        return businessService;
    }

    private ServiceManager(ServletContext context) {
        //ini service
    }
}
