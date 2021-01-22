package com.study.lab1.servlets;

import com.study.lab1.service.ServiceImplementation;
import com.study.lab1.templater.PageGenerator;
import com.study.lab1.entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddUserToListServlet extends HttpServlet {

    private ServiceImplementation serviceImplementation;

    public AddUserToListServlet(ServiceImplementation serviceImplementation) {
        this.serviceImplementation = serviceImplementation;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().println(PageGenerator.instance().getPage("add-user.html", new ArrayList<User>()));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("First-name");
        System.out.println(firstName);
        String lastName = request.getParameter("Last-name");
        System.out.println(lastName);
        int salary = parseInt(request.getParameter("Salary"));
        System.out.println(salary);

        serviceImplementation.addUser(firstName, lastName, salary, null);


            response.setContentType("text/html;charset=utf-8");

            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/users");
    }
}
