package com.epam.dfilatov.istore.filter;

import com.epam.dfilatov.istore.util.RoutingUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            String requestURL = servletRequest.getRequestURI();
            LOGGER.error("Request " + requestURL + " failed: " + e.getMessage(), e);
            RoutingUtils.forwardToPage("error.jsp", servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
