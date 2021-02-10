package com.bilous.usersstore.dao;

import com.bilous.usersstore.PropertiesReader;
import com.bilous.usersstore.dao.defaultuser.DefaultUserDAO;
import com.bilous.usersstore.service.defaultuserdervice.DefaultUserService;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBDataSource {

    private final BasicDataSource dataSource = new BasicDataSource();

    public DBDataSource(String path) {
        PropertiesReader propertiesReader = new PropertiesReader(path);

        this.dataSource.setUrl(propertiesReader.getUrl());
        this.dataSource.setUsername(propertiesReader.getUsername());
        this.dataSource.setPassword(propertiesReader.getPassword());
        this.dataSource.setDriverClassName(propertiesReader.getDriverClassName());
    }

    public DefaultUserService getServiceImplementation() {
        return new DefaultUserService(this.dataSource);
    }

    public DefaultUserDAO getDefaultUserDAO() {
        return new DefaultUserDAO(this.dataSource);
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }

}
