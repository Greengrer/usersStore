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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetUsersListServletTest {

    private final DefaultUserService defaultUserService = mock(DefaultUserService.class);

    private final GetUsersListServlet getUsersListServlet = new GetUsersListServlet(defaultUserService);

    @Test
    void doGetTest() throws IOException {
        when(defaultUserService.findAllUsers()).thenReturn(new ArrayList<>());
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