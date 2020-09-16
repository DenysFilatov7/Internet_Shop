package com.epam.dfilatov.istore.servlet.page;

import com.epam.dfilatov.istore.servlet.AbstractController;
import com.epam.dfilatov.istore.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class AllProductsController extends AbstractController {
    private static final long serialVersionUID = -7262984397655174622L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           RoutingUtils.forwardToPage("products.jsp", req, resp);

    }
}
