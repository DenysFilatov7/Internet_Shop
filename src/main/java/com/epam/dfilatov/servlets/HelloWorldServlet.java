package com.epam.dfilatov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 2098564567137183654L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("Hello world!");
        out.println("</body></html>");

        Cookie cookie = new Cookie("author", "dfilatov");
        cookie.setMaxAge(1800);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);


        out.close();

    }

}
