package com.epam.dfilatov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

@WebServlet("/test-request-response")
public class TestRequestResponseServlet extends HttpServlet {

    private static final long serialVersionUID = -327327393157983094L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setDateHeader("Date", System.currentTimeMillis());
        resp.addHeader("author", "dfilatov");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setLocale(Locale.ITALIAN);
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><body>");
        printWriter.println("<form action='/test-request-response' method='post'>");
        printWriter.println("Your name: <input name='name'><br>");
        printWriter.println("<input type='submit'");
        printWriter.println("</body></html>");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(" Request method: " + req.getMethod());
        printWriter.println("<br>Request URI: " + req.getRequestURI());
        printWriter.println("<br>Protocol version: " + req.getProtocol());
        printWriter.println("<br>Header 'User-agent': " + req.getHeader("User-Agent"));
        printWriter.println("<br>Header 'Accept-Language': " + req.getLocale());
        printWriter.println("<br>Header 'Content-Length': " + req.getContentLength());
        printWriter.println("<br>Header 'Content-Type': " + req.getContentType());
        printWriter.println("<br>Remote address: " + req.getRemoteHost());
        printWriter.println("<br>Request parameter: " + req.getParameter("name"));
        printWriter.close();

    }
}
