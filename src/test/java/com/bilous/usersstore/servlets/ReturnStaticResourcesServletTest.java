package com.bilous.usersstore.servlets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class ReturnStaticResourcesServletTest {

    private final ReturnStaticResourcesServlet returnStaticResourcesServlet = new ReturnStaticResourcesServlet();
    private static HttpServletRequest mockedRequest;
    private static HttpServletResponse mockedResponse;
    private static ServletOutputStream mockedOutputStream;

    @BeforeEach
    void beforeEach() throws IOException {
        mockedRequest = mock(HttpServletRequest.class);
        mockedResponse = mock(HttpServletResponse.class);
        mockedOutputStream = mock(ServletOutputStream.class);
        when(mockedResponse.getOutputStream()).thenReturn(mockedOutputStream);
    }

    @Test
    void doGetPageTest() throws IOException {

        when(mockedRequest.getPathInfo()).thenReturn("/add-user.html");

        returnStaticResourcesServlet.doGet(mockedRequest, mockedResponse);
        verify(mockedResponse).getOutputStream();

    }

    @Test
    void doGetPictureTest() throws IOException {

        when(mockedRequest.getPathInfo()).thenReturn("/1000.png");

        returnStaticResourcesServlet.doGet(mockedRequest, mockedResponse);
        verify(mockedResponse).getOutputStream();

    }
}