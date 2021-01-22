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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetUsersListServletTest {

    ServiceImplementation serviceImplementation = mock(ServiceImplementation.class);

    public GetUsersListServlet getUsersListServlet = new GetUsersListServlet(serviceImplementation);

    @Test
    void doGetTest() throws IOException {
        when(serviceImplementation.getAllUsers()).thenReturn(new ArrayList<User>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        when(mockedResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));

        getUsersListServlet.doGet(mockedRequest, mockedResponse);

        InOrder inOrder = Mockito.inOrder(mockedResponse);
        inOrder.verify(mockedResponse).getWriter();
        inOrder.verify(mockedResponse).setContentType("text/html;charset=utf-8");
        inOrder.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
    }
}