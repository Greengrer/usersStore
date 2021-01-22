package com.study.lab1.service;

import com.study.lab1.dao.UserDAOImplementation;
import com.study.lab1.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class ServiceImplementation implements Service {

    private UserDAOImplementation userDAOImplementation;

    public ServiceImplementation(BasicDataSource dataSource) {
        this.userDAOImplementation = new UserDAOImplementation(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        //might be logic like filtering users etc.
        return userDAOImplementation.getAllUsers();
    }

    @Override
    public void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        userDAOImplementation.addUser(firstName, lastName, salary, dateOfBirth);
    }

    @Override
    public void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        userDAOImplementation.updateUser(id, firstName, lastName, salary, dateOfBirth);
    }

    @Override
    public List<User> getOneUser(int id) {
        return userDAOImplementation.getOneUser(id);
    }

    @Override
    public List<User> getFilteredUsers(String firstName, String lastName) {

        List<User> filteredList = userDAOImplementation.getAllUsers();
        Iterator<User> iterator = filteredList.iterator();
        while (iterator.hasNext()) {
            if ((firstName.isEmpty() ? false : !(iterator.next().getFirstName()).equals(firstName)) || (lastName.isEmpty() ? false : !(iterator.next().getLastName()).equals(lastName))) {
                iterator.remove();
            }
        }

        return filteredList;

    }

    @Override
    public void deleteUser(int id) {
        userDAOImplementation.deleteUser(id);
    }
}
