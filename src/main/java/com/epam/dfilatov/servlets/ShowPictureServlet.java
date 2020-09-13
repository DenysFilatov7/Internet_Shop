package com.epam.dfilatov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(value = "/image/*", initParams = {@WebInitParam(name = "ROOT_DIR", value = "E:\\image")})
public class ShowPictureServlet extends HttpServlet {
    private String rootDir;

    @Override
    public void init() throws ServletException {
        rootDir = getServletConfig().getInitParameter("ROOT_DIR");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");
        String[] parts = req.getRequestURI().split("/");
        String fileName = parts[parts.length-1];
        File file = new File(rootDir + fileName);
        if(file.exists()){
            try(InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            OutputStream outputStream = new BufferedOutputStream(resp.getOutputStream())){
                int data = -1;
                while ((data = inputStream.read()) != -1){
                    outputStream.write(data);
                }
                outputStream.flush();
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
