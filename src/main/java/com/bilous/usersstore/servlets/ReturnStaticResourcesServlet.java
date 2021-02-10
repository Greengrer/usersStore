package com.bilous.usersstore.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.io.OutputStream;

public class ReturnStaticResourcesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String pathToRequestedFile = request.getPathInfo();

        if (pathToRequestedFile.equals("/")) {
            pathToRequestedFile = "/add-user.html";
        }
        try {
            pathToRequestedFile = "/templates" + pathToRequestedFile;
            InputStream inputStream = getClass().getResourceAsStream(pathToRequestedFile);

            OutputStream outputStream = response.getOutputStream();
            int readByte;
            while ((readByte = inputStream.read()) != -1) {
                outputStream.write(readByte);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong with loading or writing " + pathToRequestedFile + " into response", e);
        }
    }
}
