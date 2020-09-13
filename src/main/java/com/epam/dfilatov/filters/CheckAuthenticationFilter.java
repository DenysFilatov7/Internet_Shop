package com.epam.dfilatov.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CheckAuthenticationFilter")
public class CheckAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getSession().getAttribute("IS_AUTHENTICATED") != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String requestURL = request.getRequestURI();
            request.getSession().setAttribute("REDIRECT_URL_AFTER_SIGNIN", requestURL);
            ((HttpServletResponse) servletResponse).sendRedirect("/sign-in");
        }
    }

    @Override
    public void destroy() {

    }
}
