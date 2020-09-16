package com.epam.dfilatov.istore.filter;

import com.epam.dfilatov.istore.util.UrlUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {
    protected final Logger LOGGER = Logger.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public abstract void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    @Override
    public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (UrlUtils.isMediaUrl(url) || UrlUtils.isStaticUrl(url)) {
            filterChain.doFilter(request, response);
        } else {
            doFilter(request, response, filterChain);
        }
    }

    @Override
    public void destroy() {

    }
}
