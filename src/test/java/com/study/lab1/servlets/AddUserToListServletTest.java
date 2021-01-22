package com.study.lab1.servlets;

import com.study.lab1.entity.User;
import com.study.lab1.service.ServiceImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class AddUserToListServletTest {

    ServiceImplementation serviceImplementation = mock(ServiceImplementation.class);

    public AddUserToListServlet addUserToListServlet = new AddUserToListServlet(serviceImplementation);

    @Test
    void doGet() throws IOException {
        when(serviceImplementation.getAllUsers()).thenReturn(new ArrayList<User>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        when(mockedResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));

        addUserToListServlet.doGet(mockedRequest, mockedResponse);

        InOrder inOrder = Mockito.inOrder(mockedResponse);
        inOrder.verify(mockedResponse).getWriter();
        inOrder.verify(mockedResponse).setContentType("text/html;charset=utf-8");
        inOrder.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doPost() throws IOException {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        when(mockedRequest.getParameter("First-name")).thenReturn("Nastya");
        when(mockedRequest.getParameter("Last-name")).thenReturn("Bilou");
        when(mockedRequest.getParameter("Salary")).thenReturn("0");

        addUserToListServlet.doPost(mockedRequest, mockedResponse);

        InOrder inOrderRequest = Mockito.inOrder(mockedRequest);
        inOrderRequest.verify(mockedRequest).getParameter("First-name");
        inOrderRequest.verify(mockedRequest).getParameter("Last-name");
        inOrderRequest.verify(mockedRequest).getParameter("Salary");
        InOrder inOrderResponse = Mockito.inOrder(mockedResponse);
        inOrderResponse.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
        inOrderResponse.verify(mockedResponse).sendRedirect("/users");
        verify(serviceImplementation).addUser( "Nastya", "Bilou", 0, null);
    }
}