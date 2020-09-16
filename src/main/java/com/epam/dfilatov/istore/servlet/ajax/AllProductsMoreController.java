package com.epam.dfilatov.istore.servlet.ajax;

import com.epam.dfilatov.istore.servlet.AbstractController;
import com.epam.dfilatov.istore.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/html/more/products")
public class AllProductsMoreController extends AbstractController {

    private static final long serialVersionUID = -7345589340887877858L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp );
    }
}
