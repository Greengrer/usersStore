package com.bilous.usersstore.service.defaultuserdervice;

import com.bilous.usersstore.dao.defaultuser.DefaultUserDAO;
import com.bilous.usersstore.entity.User;
import com.bilous.usersstore.service.Service;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.util.List;

public class DefaultUserService implements Service {

    private final DefaultUserDAO defaultUserDAO;

    public DefaultUserService(BasicDataSource dataSource) {
        this.defaultUserDAO = new DefaultUserDAO(dataSource);
    }

    @Override
    public List<User> findAllUsers() {
        return defaultUserDAO.findAllUsers();
    }

    @Override
    public void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        defaultUserDAO.addUser(firstName, lastName, salary, dateOfBirth);
    }

    @Override
    public void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        defaultUserDAO.updateUser(id, firstName, lastName, salary, dateOfBirth);
    }

    @Override
    public List<User> findOneUser(int id) {
        return defaultUserDAO.findOneUser(id);
    }

    @Override
    public List<User> findFilteredUsers(String matcher) {

        int indexOfDivider = matcher.indexOf(' ');

        if (indexOfDivider == -1) {
            return defaultUserDAO.findFilteredByOneStringUsers(matcher);
        } else {
            return defaultUserDAO.findFilteredByTwoStringsUsers(matcher.substring(0, indexOfDivider), matcher.substring(indexOfDivider + 1));
        }
    }

    @Override
    public void deleteUser(int id) {
        defaultUserDAO.deleteUser(id);
    }

}
