package com.epam.dfilatov.istore.listener;

import com.epam.dfilatov.istore.service.impl.ServiceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class IStoreApplicationListener implements ServletContextListener {
    protected static final Logger LOGGER = Logger.getLogger(IStoreApplicationListener.class);
    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            serviceManager = ServiceManager.getInstance(servletContextEvent.getServletContext());
        } catch (RuntimeException e) {
            LOGGER.error("Can't download properties file", e);
            throw e;
        }
        LOGGER.info("Web application 'IStore' initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        serviceManager.close();
        LOGGER.info("Web application 'IStore' destroyed");
    }
}
