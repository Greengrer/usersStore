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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetFilteredUsersServletTest {

    ServiceImplementation serviceImplementation = mock(ServiceImplementation.class);

    public GetFilteredUsersServlet getFilteredUsersServlet = new GetFilteredUsersServlet(serviceImplementation);

    @Test
    void doGetEmptySearchTest() throws IOException {
        when(serviceImplementation.getAllUsers()).thenReturn(new ArrayList<User>());
        when(serviceImplementation.getFilteredUsers(anyString(), anyString())).thenReturn(new ArrayList<User>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        when(mockedResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(mockedRequest.getParameter("First-name")).thenReturn("");
        when(mockedRequest.getParameter("Last-name")).thenReturn("");

        getFilteredUsersServlet.doGet(mockedRequest, mockedResponse);

        InOrder inOrderRequest = Mockito.inOrder(mockedRequest);
        inOrderRequest.verify(mockedRequest).getParameter("First-name");
        inOrderRequest.verify(mockedRequest).getParameter("Last-name");

        InOrder inOrderResponse = Mockito.inOrder(mockedResponse);
        inOrderResponse.verify(mockedResponse).setContentType("text/html;charset=utf-8");
        inOrderResponse.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
        inOrderResponse.verify(mockedResponse).sendRedirect("/users");
    }

    @Test
    void doGetNotEmptySearchTest() throws IOException {
        when(serviceImplementation.getAllUsers()).thenReturn(new ArrayList<User>());
        when(serviceImplementation.getFilteredUsers(anyString(), anyString())).thenReturn(new ArrayList<User>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        when(mockedResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(mockedRequest.getParameter("First-name")).thenReturn("a");
        when(mockedRequest.getParameter("Last-name")).thenReturn("");

        getFilteredUsersServlet.doGet(mockedRequest, mockedResponse);

        InOrder inOrderRequest = Mockito.inOrder(mockedRequest);
        inOrderRequest.verify(mockedRequest).getParameter("First-name");
        inOrderRequest.verify(mockedRequest).getParameter("Last-name");

        InOrder inOrderResponse = Mockito.inOrder(mockedResponse);
        inOrderResponse.verify(mockedResponse).getWriter();
        inOrderResponse.verify(mockedResponse).setContentType("text/html;charset=utf-8");
        inOrderResponse.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);

    }
}