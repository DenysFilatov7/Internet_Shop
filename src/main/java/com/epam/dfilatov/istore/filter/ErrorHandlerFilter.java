package com.epam.dfilatov.istore.filter;

import com.epam.dfilatov.istore.util.RoutingUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/")
public class ErrorHandlerFilter extends AbstractFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e){
            String requestURL = request.getRequestURI();
//            LOGGER.error("Request " + requestURL + " failed: " + e.getMessage(), e);
            RoutingUtils.forwardToPage("error.jsp", request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
