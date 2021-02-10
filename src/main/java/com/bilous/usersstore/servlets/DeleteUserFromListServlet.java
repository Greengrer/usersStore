package com.bilous.usersstore.servlets;

import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class DeleteUserFromListServlet extends HttpServlet {

    private final DefaultUserService defaultUserService;

    public DeleteUserFromListServlet(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        defaultUserService.deleteUser(id);
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.sendRedirect("/users");
        } catch (IOException e) {
            throw new RuntimeException("Failed redirect to /users ", e);
        }
    }

}
