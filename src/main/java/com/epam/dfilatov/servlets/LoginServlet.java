package com.epam.dfilatov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter printWriter = resp.getWriter()) {
            if (req.getAttribute("error") != null) {
                printWriter.println("<h5 style='color:red'>" + req.getAttribute("error") + "</h5>");
            }
            printWriter.println("<form action='/login' method='post'>");
            printWriter.println("<input name='login' placeHolder='Login'>");
            printWriter.println("<input name='password' placeHolder='Password' type='password'>");
            printWriter.println("<input type='submit' value='Login'");
            printWriter.println("</form>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (isValid(req, login, password)) {
            resp.sendRedirect("/my-private-page");
        } else {
            doGet(req, resp);
        }
    }

    private boolean isValid(HttpServletRequest req, String login, String password) {
        if (login == null || login.trim().isEmpty()) {
            req.setAttribute("error", "Login is required");
            return false;
        }
        if(password == null || password.trim().isEmpty()){
            req.setAttribute("error", "Password is required");
            return false;
        }
        return true;
    }
}
