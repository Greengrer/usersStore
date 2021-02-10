package com.bilous.usersstore;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public PropertiesReader(String path) {
        InputStream inputStream = getClass().getResourceAsStream(path);
        setProperties(inputStream);
    }

    private final Properties properties = new Properties();

    private void setProperties(InputStream inputStream) {
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Was unable to load properties", e);
        }
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getDriverClassName() {
        return properties.getProperty("driver-class-name");
    }

    public String getPort() {
        return properties.getProperty("port");
    }

}

