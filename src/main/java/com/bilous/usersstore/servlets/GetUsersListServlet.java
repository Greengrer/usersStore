package com.bilous.usersstore.servlets;

import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import com.bilous.usersstore.templater.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetUsersListServlet extends HttpServlet {

    private final DefaultUserService defaultUserService;

    public GetUsersListServlet(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.getWriter().println(PageGenerator.instance()
                    .getPage("users.html", defaultUserService.findAllUsers()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load users.html ", e);
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
