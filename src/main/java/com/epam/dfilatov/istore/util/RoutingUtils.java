package com.epam.dfilatov.istore.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoutingUtils {
    public static void forwardToFragment(String jspFragment, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/fragment/" + jspFragment).forward(request, response);
    }

    public static void forwardToPage(String jspPage, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("currentPage", "page/" + jspPage);
        request.getRequestDispatcher("/WEB-INF/jsp/page-template.jsp").forward(request, response);
    }

    public static void sendHTMLFragment(String text, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(text);
        response.getWriter().close();
    }

    public static void redirect(String url, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(url);
    }

}
