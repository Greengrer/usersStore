package com.bilous.usersstore.templater;

import com.bilous.usersstore.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageGenerator {

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, List<User> userList) {
        Writer stream = new StringWriter();
        Map<String, List<User>> mapWithList = new HashMap<>();
        mapWithList.put("users", userList);
        try {
            Template template = cfg.getTemplate(filename);
            template.process(mapWithList, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    public String getPage(String filename, Map<String, Object> mapWithList) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(filename);
            template.process(mapWithList, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration(Configuration.VERSION_2_3_30);

        cfg.setClassForTemplateLoading(this.getClass(), "/templates");

        cfg.setNumberFormat("0.######");
    }
}
