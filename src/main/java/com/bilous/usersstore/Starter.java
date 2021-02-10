package com.bilous.usersstore;

import com.bilous.usersstore.dao.DBDataSource;
import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import com.bilous.usersstore.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static java.lang.Integer.parseInt;

public class Starter {

    public static void main(String[] args) throws Exception {

        DBDataSource dbDataSource = new DBDataSource("/application.properties");

        DefaultUserService defaultUserService = dbDataSource.getServiceImplementation();

        PropertiesReader propertiesReader = new PropertiesReader("/application.properties");

        GetUsersListServlet getUsersListServlet = new GetUsersListServlet(defaultUserService);
        AddUserToListServlet addUserToListServlet = new AddUserToListServlet(defaultUserService);
        DeleteUserFromListServlet deleteUserFromListServlet = new DeleteUserFromListServlet(defaultUserService);
        EditUserServlet editUserServlet = new EditUserServlet(defaultUserService);
        GetFilteredUsersServlet getFilteredUsersServlet = new GetFilteredUsersServlet(defaultUserService);
        ReturnStaticResourcesServlet returnStaticResourcesServlet = new ReturnStaticResourcesServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(getUsersListServlet), "/users");
        context.addServlet(new ServletHolder(addUserToListServlet), "/add");
        context.addServlet(new ServletHolder(deleteUserFromListServlet), "/delete");
        context.addServlet(new ServletHolder(editUserServlet), "/edit");
        context.addServlet(new ServletHolder(getFilteredUsersServlet), "/filter");
        context.addServlet(new ServletHolder(returnStaticResourcesServlet), "/*");
        Server server = new Server(parseInt(propertiesReader.getPort()));
        server.setHandler(context);
        server.start();
    }
}
