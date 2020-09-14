package com.epam.dfilatov.istore.listener;

import com.epam.dfilatov.istore.service.ServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class IStoreApplicationListener implements ServletContextListener {
    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        serviceManager = ServiceManager.getInstance(servletContextEvent.getServletContext());
        serviceManager.getBusinessService().doSomething();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        serviceManager.getBusinessService().doSomething();
        serviceManager.close();
    }
}
