package com.epam.dfilatov.istore.servlet;

import com.epam.dfilatov.istore.service.OrderService;
import com.epam.dfilatov.istore.service.ProductService;
import com.epam.dfilatov.istore.service.impl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AbstractController extends HttpServlet {
    private static final long serialVersionUID = 8670131483871092214L;

    private ProductService productService;
    private OrderService orderService;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
    }

    public final ProductService getProductService() {
        return productService;
    }

    public final void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public final OrderService getOrderService() {
        return orderService;
    }

    public final void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
