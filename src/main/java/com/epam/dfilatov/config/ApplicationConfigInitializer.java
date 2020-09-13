package com.epam.dfilatov.config;

import com.epam.dfilatov.servlets.JavaConfigServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class ApplicationConfigInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        JavaConfigServlet servlet = new JavaConfigServlet();
        ServletRegistration.Dynamic servletConfig = servletContext.addServlet("JavaConfigServlet", servlet);
        servletConfig.addMapping("/java");
    }
}
