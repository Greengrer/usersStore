package com.study.lab1;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    public PropertiesReader(String path) {
        setProperties(path);
    }

    private Properties properties = new Properties();

    private void setProperties(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getUrl(){
        return properties.getProperty("url");
    }

    public String getUsername(){
        return properties.getProperty("username");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }

    public String getDriverClassName(){
        return properties.getProperty("driver-class-name");
    }

}

