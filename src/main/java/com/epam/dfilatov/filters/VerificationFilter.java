package com.epam.dfilatov.filters;

import com.epam.dfilatov.istore.service.BussinessService;
import com.epam.dfilatov.istore.service.ServiceManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/")
public class VerificationFilter implements Filter {
    private BussinessService bussinessService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        bussinessService = ServiceManager.getInstance(filterConfig.getServletContext()).getBusinessService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        bussinessService.doSomething();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
