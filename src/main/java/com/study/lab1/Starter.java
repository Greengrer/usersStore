package com.study.lab1;

import com.study.lab1.service.ServiceImplementation;
import com.study.lab1.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

public class Starter {

    public static void main(String[] args) throws Exception {

        DBDataSource dbDataSource = new DBDataSource("src/main/resources/server.properties");

        ServiceImplementation serviceImplementation = dbDataSource.getServiceImplementation();



        GetUsersListServlet getUsersListServlet = new GetUsersListServlet(serviceImplementation);
        AddUserToListServlet addUserToListServlet = new AddUserToListServlet(serviceImplementation);
        DeleteUserFromListServlet deleteUserFromListServlet = new DeleteUserFromListServlet(serviceImplementation);
        EditUserServlet editUserServlet = new EditUserServlet(serviceImplementation);
        GetFilteredUsersServlet getFilteredUsersServlet = new GetFilteredUsersServlet(serviceImplementation);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder((Servlet) getUsersListServlet), "/users");
        context.addServlet(new ServletHolder((Servlet) addUserToListServlet), "/add");
        context.addServlet(new ServletHolder((Servlet) deleteUserFromListServlet), "/delete");
        context.addServlet(new ServletHolder((Servlet) editUserServlet), "/edit");
        context.addServlet(new ServletHolder((Servlet) getFilteredUsersServlet), "/filter");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
