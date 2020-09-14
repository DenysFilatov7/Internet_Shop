package com.epam.dfilatov.istore.servlet;

import com.epam.dfilatov.istore.service.BussinessService;
import com.epam.dfilatov.istore.service.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AbstractController extends HttpServlet {
    private static final long serialVersionUID = 8670131483871092214L;

    private BussinessService businessService;

    @Override
    public final void init() throws ServletException {
        businessService = ServiceManager.getInstance(getServletContext()).getBusinessService();
    }

    public final BussinessService getBusinessService() {
        return businessService;
    }
}
