package com.bilous.usersstore.servlets;

import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import com.bilous.usersstore.templater.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class EditUserServlet extends HttpServlet {

    private final DefaultUserService defaultUserService;

    public EditUserServlet(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        try {
            response.getWriter().println(PageGenerator.instance()
                    .getPage("edit-user.html", defaultUserService.findOneUser(id)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load edit-user.html with id  " + id, e);
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        defaultUserService.updateUser(parseInt(request.getParameter("id")),
                request.getParameter("First-name"),
                request.getParameter("Last-name"),
                parseInt(request.getParameter("Salary")),
                null);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.sendRedirect("/users");
        } catch (IOException e) {
            throw new RuntimeException("Failed redirect to /users ", e);
        }
    }

}
