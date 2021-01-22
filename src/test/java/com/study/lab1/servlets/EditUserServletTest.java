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

class EditUserServletTest {

    ServiceImplementation serviceImplementation = mock(ServiceImplementation.class);

    public EditUserServlet editUserServlet = new EditUserServlet(serviceImplementation);


    @Test
    void doPostTest() throws IOException {
        when(serviceImplementation.getAllUsers()).thenReturn(new ArrayList<User>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        when(mockedRequest.getParameter("id")).thenReturn("-1");
        when(mockedRequest.getParameter("First-name")).thenReturn("Nastya");
        when(mockedRequest.getParameter("Last-name")).thenReturn("Bilou");
        when(mockedRequest.getParameter("Salary")).thenReturn("0");

        editUserServlet.doPost(mockedRequest, mockedResponse);

        InOrder inOrderRequest = Mockito.inOrder(mockedRequest);
        inOrderRequest.verify(mockedRequest, times(2)).getParameter("id");
        inOrderRequest.verify(mockedRequest).getParameter("First-name");
        inOrderRequest.verify(mockedRequest).getParameter("Last-name");
        inOrderRequest.verify(mockedRequest).getParameter("Salary");
        InOrder inOrderResponse = Mockito.inOrder(mockedResponse);
        inOrderResponse.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
        inOrderResponse.verify(mockedResponse).sendRedirect("/users");
        verify(serviceImplementation).updateUser(-1, "Nastya", "Bilou", 0, null);
    }

    @Test
    void doGetTest() throws IOException {
        ArrayList<User> listWithSingleUserForMock = new ArrayList<User>();
        listWithSingleUserForMock.add(new User(-1, "Nastya", "Bilou", 0));
        when(serviceImplementation.getOneUser(-1)).thenReturn(listWithSingleUserForMock);
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        when(mockedRequest.getParameter("id")).thenReturn("-1");
        StringWriter stringWriter = new StringWriter();
        when(mockedResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));

        editUserServlet.doGet(mockedRequest, mockedResponse);

        InOrder inOrder = Mockito.inOrder(mockedResponse);
        inOrder.verify(mockedResponse).getWriter();
        inOrder.verify(mockedResponse).setContentType("text/html;charset=utf-8");
        inOrder.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
    }
}