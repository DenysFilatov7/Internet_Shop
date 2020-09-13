package com.epam.dfilatov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-world")
public class HelloWorld extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String attr1 = (String) req.getSession().getAttribute("attr1");
        req.getSession().removeAttribute("attr1");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Hello World " + attr1);
//        printWriter.close();
    }
}
