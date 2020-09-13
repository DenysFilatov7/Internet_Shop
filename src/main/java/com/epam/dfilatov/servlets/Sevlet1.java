package com.epam.dfilatov.servlets;

import com.epam.dfilatov.model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/serv1")
public class Sevlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("model", new Model());
        req.getRequestDispatcher("/WEB-INF/model.jsp").forward(req, resp);
    }
}
