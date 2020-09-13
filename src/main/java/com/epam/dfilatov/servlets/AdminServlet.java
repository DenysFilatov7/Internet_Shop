package com.epam.dfilatov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/admin", initParams = {
        @WebInitParam(name = "IP", value = "0:0:0:0:0:0:0:1"),
        @WebInitParam(name = "ACCESS_KEY", value = "ACCESS_KEY"),
        @WebInitParam(name = "login", value = "admin"),
        @WebInitParam(name = "password", value = "password")})
public class AdminServlet extends HttpServlet {
    private String ipAddress;
    private String accessKey;
    private String login;
    private String password;

    @Override
    public void init() throws ServletException {
        ipAddress = getServletConfig().getInitParameter("IP");
        accessKey = getServletConfig().getInitParameter("ACCESS_KEY");
        login = getServletConfig().getInitParameter("login");
        password = getServletConfig().getInitParameter("password");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String ip = req.getRemoteAddr();
        String accessKey = req.getHeader("ACCESS_KEY");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try{
            validate(ip, accessKey, login, password);
            resp.getWriter().println("OK");
        }catch (IllegalStateException e){
            System.err.println(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("FAILED");
        }
    }

    private void validate(String ip, String accessKey, String login, String password) {
        StringBuilder errors = new StringBuilder();
        if(this.ipAddress.equals(ip)){
            System.out.println("Login via ip:" + ip);
            return;
        }else{
            errors.append(String.format("Invalid ip : %s\n", ip));
        }
        if(this.accessKey.equals(accessKey)){
            System.out.println("Login via accessKey : " + accessKey);
            return;
        }else {
            errors.append(String.format("Invalid access key : %s\n", accessKey));
        }
        if(this.login.equals(login) && this.password.equals(password)){
            System.out.println("Login with login and password : " + login + ", " + password);
            return;
        }else {
            errors.append(String.format("Invalid login and password: " + login + ", " + password));
        }
        throw new IllegalStateException(errors.toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
