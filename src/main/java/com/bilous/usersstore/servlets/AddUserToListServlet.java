package com.bilous.usersstore.servlets;

import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import com.bilous.usersstore.templater.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddUserToListServlet extends HttpServlet {

    private final DefaultUserService defaultUserService;

    public AddUserToListServlet(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.getWriter().println(PageGenerator.instance().getPage("add-user.html", new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load add-user, because:", e);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String firstName = request.getParameter("First-name");
        String lastName = request.getParameter("Last-name");
        int salary = parseInt(request.getParameter("Salary"));

        defaultUserService.addUser(firstName, lastName, salary, null);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.sendRedirect("/users");
        } catch (IOException e) {
            throw new RuntimeException("Failed redirect to /users ", e);
        }
    }

}
