package com.study.lab1.dao;

import com.study.lab1.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    public void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth);

    public void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth);

    public List<User> getOneUser(int id);

    public void deleteUser(int id);
}
