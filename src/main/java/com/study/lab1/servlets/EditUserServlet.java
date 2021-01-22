package com.study.lab1.servlets;

import com.study.lab1.service.ServiceImplementation;
import com.study.lab1.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class EditUserServlet extends HttpServlet {

    private ServiceImplementation serviceImplementation;

    public EditUserServlet(ServiceImplementation serviceImplementation) {
        this.serviceImplementation = serviceImplementation;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            int id = parseInt(request.getParameter("id"));
            response.getWriter().println(PageGenerator.instance().getPage("edit-user.html", serviceImplementation.getOneUser(id)));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int index = parseInt(request.getParameter("id"));

            serviceImplementation.updateUser(parseInt(request.getParameter("id")), request.getParameter("First-name"), request.getParameter("Last-name"), parseInt(request.getParameter("Salary")), null);

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/users");
    }
}
