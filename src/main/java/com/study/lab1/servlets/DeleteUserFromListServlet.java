package com.study.lab1.servlets;

import com.study.lab1.service.ServiceImplementation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class DeleteUserFromListServlet extends HttpServlet {

    private ServiceImplementation serviceImplementation;

    public DeleteUserFromListServlet(ServiceImplementation serviceImplementation) {
        this.serviceImplementation = serviceImplementation;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

            int id = parseInt(request.getParameter("id"));
            serviceImplementation.deleteUser(id);
            response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("/users");
    }

}
