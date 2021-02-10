package com.bilous.usersstore.servlets;

import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import com.bilous.usersstore.templater.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetFilteredUsersServlet extends HttpServlet {

    private final DefaultUserService defaultUserService;

    public GetFilteredUsersServlet(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String matcher = request.getParameter("search");

        if (matcher.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                response.sendRedirect("/users");
            } catch (IOException e) {
                throw new RuntimeException("Failed redirect to /users ", e);
            }
        } else {

            try {
                Map<String, Object> mapWithList = new HashMap<>();
                mapWithList.put("users", defaultUserService.findFilteredUsers(matcher));
                mapWithList.put("matcher", matcher);
                response.getWriter().println(PageGenerator.instance().getPage("filtered-users.html",
                        mapWithList));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load users.html page with list filtered by "
                        + matcher, e);
            }
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
