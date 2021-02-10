package com.bilous.usersstore.servlets;

import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class DeleteUserFromListServletTest {

    private final DefaultUserService defaultUserService = mock(DefaultUserService.class);

    private final DeleteUserFromListServlet deleteUserFromListServlet = new DeleteUserFromListServlet(defaultUserService);

    @Test
    void doPostTest() throws IOException {
        when(defaultUserService.findAllUsers()).thenReturn(new ArrayList<>());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        when(mockedRequest.getParameter("id")).thenReturn("1");

        deleteUserFromListServlet.doPost(mockedRequest, mockedResponse);

        verify(mockedRequest).getParameter("id");
        InOrder inOrder = Mockito.inOrder(mockedResponse);
        inOrder.verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);
        inOrder.verify(mockedResponse).sendRedirect("/users");
        verify(defaultUserService).deleteUser(1);
    }

}