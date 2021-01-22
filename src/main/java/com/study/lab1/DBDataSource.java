package com.study.lab1;

import com.study.lab1.dao.UserDAOImplementation;
import com.study.lab1.service.ServiceImplementation;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;

public class DBDataSource {

    private BasicDataSource dataSource = new BasicDataSource();

    public DBDataSource(String path) {
        PropertiesReader propertiesReader = new PropertiesReader(path);
        Properties serverProperties = propertiesReader.getProperties();

        this.dataSource.setUrl(serverProperties.getProperty("url"));
        this.dataSource.setUsername(serverProperties.getProperty("username"));
        this.dataSource.setPassword(serverProperties.getProperty("password"));
        this.dataSource.setDriverClassName(serverProperties.getProperty("driver-class-name"));
    }

    public ServiceImplementation getServiceImplementation() {
        return new ServiceImplementation(this.dataSource);
    }

    public UserDAOImplementation getUserDAOImplementation() {
        return new UserDAOImplementation(this.dataSource);
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
