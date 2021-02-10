package com.bilous.usersstore.servlets;

import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetFilteredUsersServletTest {

    private final DefaultUserService defaultUserService = mock(DefaultUserService.class);

    private final GetFilteredUsersServlet getFilteredUsersServlet = new GetFilteredUsersServlet(defaultUserService);

    @Test
    void doGetEmptySearchTest() throws IOException {
        when(defaultUserService.findAllUsers()).thenReturn(new ArrayList<>());
        when(defaultUserService.findFilteredUsers(anyString())).thenReturn(new ArrayList<>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        when(mockedResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(mockedRequest.getParameter("search")).thenReturn("");

        getFilteredUsersServlet.doGet(mockedRequest, mockedResponse);

        InOrder inOrderRequest = Mockito.inOrder(mockedRequest);
        inOrderRequest.verify(mockedRequest).getParameter("search");

        InOrder inOrderResponse = Mockito.inOrder(mockedResponse);
        inOrderResponse.verify(mockedResponse).setContentType("text/html;charset=utf-8");
        inOrderResponse.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
        inOrderResponse.verify(mockedResponse).sendRedirect("/users");
    }

    @Test
    void doGetNotEmptySearchTest() throws IOException {
        when(defaultUserService.findAllUsers()).thenReturn(new ArrayList<>());
        when(defaultUserService.findFilteredUsers(anyString())).thenReturn(new ArrayList<>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        when(mockedResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(mockedRequest.getParameter("search")).thenReturn("a");

        getFilteredUsersServlet.doGet(mockedRequest, mockedResponse);

        InOrder inOrderRequest = Mockito.inOrder(mockedRequest);
        inOrderRequest.verify(mockedRequest).getParameter("search");

        InOrder inOrderResponse = Mockito.inOrder(mockedResponse);
        inOrderResponse.verify(mockedResponse).getWriter();
        inOrderResponse.verify(mockedResponse).setContentType("text/html;charset=utf-8");
        inOrderResponse.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
    }

}