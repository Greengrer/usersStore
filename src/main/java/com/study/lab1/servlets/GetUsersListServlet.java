package com.study.lab1.servlets;

import com.study.lab1.service.ServiceImplementation;
import com.study.lab1.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUsersListServlet extends HttpServlet {

    private ServiceImplementation serviceImplementation;

    public GetUsersListServlet(ServiceImplementation serviceImplementation) {
        this.serviceImplementation = serviceImplementation;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().println(PageGenerator.instance().getPage("users.html", serviceImplementation.getAllUsers()));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
