package com.epam.dfilatov.istore.servlet.page;

import com.epam.dfilatov.istore.servlet.AbstractController;
import com.epam.dfilatov.istore.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorController extends AbstractController {
    private static final long serialVersionUID = -496421767098777492L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToPage("error.jsp", req, resp);
    }
}
