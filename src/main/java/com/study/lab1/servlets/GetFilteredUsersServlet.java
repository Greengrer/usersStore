package com.study.lab1.servlets;

import com.study.lab1.service.ServiceImplementation;
import com.study.lab1.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class GetFilteredUsersServlet extends HttpServlet {
    private ServiceImplementation serviceImplementation;

    public GetFilteredUsersServlet(ServiceImplementation serviceImplementation) {
        this.serviceImplementation = serviceImplementation;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("First-name");
        String lastName = request.getParameter("Last-name");

        if (firstName.isEmpty() && lastName.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/users");
        } else {

            response.getWriter().println(PageGenerator.instance().getPage("users.html", serviceImplementation.getFilteredUsers(firstName, lastName)));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
