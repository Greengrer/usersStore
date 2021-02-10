/*
package com.study.lab1.templater;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.parser.HTMLParser;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import com.study.lab1.entity.User;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PageGeneratorTest {

    @Test
    void getPageTest() throws MalformedURLException {
        ArrayList<User> listWithSingleUser = new ArrayList<User>();
        listWithSingleUser.add(new User(-1, "Nastya", "Bilou", 0));
        String fileName = "users.html";

        StringWebResponse response = new StringWebResponse(PageGenerator.instance().getPage(fileName, listWithSingleUser), new URL("www.example.com"));
        WebClient client = new WebClient();
        HtmlPage page = HTMLParser.parseHtml(response, client.getCurrentWindow());

    }
}*/
