package com.epam.dfilatov.istore.service.impl;

import com.epam.dfilatov.istore.service.OrderService;
import com.epam.dfilatov.istore.service.ProductService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceManager {
    private final ProductService productService;
    private final OrderService orderService;
    private final Properties applicationProperties = new Properties();
    public static ServiceManager getInstance(ServletContext servletContext) {
        ServiceManager instance = (ServiceManager) servletContext.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(servletContext);
            servletContext.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public String getApplicationProperty(String key){
        return applicationProperties.getProperty(key);
    }

    public void close() {//close resources
    }

    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl();
    }

    private void loadApplicationProperties(){
        try(InputStream inputStream = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")){
            applicationProperties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
