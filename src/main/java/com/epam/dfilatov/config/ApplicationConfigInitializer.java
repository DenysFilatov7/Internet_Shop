package com.epam.dfilatov.config;

import com.epam.dfilatov.filters.SimpleFilter;
import com.epam.dfilatov.servlets.JavaConfigServlet;

import javax.servlet.*;
import java.util.Set;

public class ApplicationConfigInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        JavaConfigServlet servlet = new JavaConfigServlet();
        ServletRegistration.Dynamic servletConfig = servletContext.addServlet("JavaConfigServlet", servlet);
        servletConfig.addMapping("/java");
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("SimpleFilter", new SimpleFilter());
        filterRegistration.addMappingForUrlPatterns(null, true, "/*");
    }
}
