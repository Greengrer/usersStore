package com.study.lab1.service;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.study.lab1.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface Service {

    public List<User> getAllUsers();

    public void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth);

    public void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth);

    public List<User> getOneUser(int id);

    public List<User> getFilteredUsers(String firstName, String lastName);

    public void deleteUser(int id);

}
