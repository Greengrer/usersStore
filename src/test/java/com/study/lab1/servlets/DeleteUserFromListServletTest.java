package com.study.lab1.servlets;

import com.study.lab1.entity.User;
import com.study.lab1.service.ServiceImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class DeleteUserFromListServletTest {

    ServiceImplementation serviceImplementation = mock(ServiceImplementation.class);

    public DeleteUserFromListServlet deleteUserFromListServlet = new DeleteUserFromListServlet(serviceImplementation);

    @Test
    void doPost() throws IOException {
        when(serviceImplementation.getAllUsers()).thenReturn(new ArrayList<User>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        when(mockedRequest.getParameter("id")).thenReturn("-1");

        deleteUserFromListServlet.doPost(mockedRequest, mockedResponse);

        verify(mockedRequest).getParameter("id");
        InOrder inOrder = Mockito.inOrder(mockedResponse);
        inOrder.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
        inOrder.verify(mockedResponse).sendRedirect("/users");
        verify(serviceImplementation).deleteUser(-1);

    }
}