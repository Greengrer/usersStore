package com.bilous.usersstore.service;

import com.bilous.usersstore.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface Service {

    List<User> findAllUsers();

    void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth);

    void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth);

    List<User> findOneUser(int id);

    List<User> findFilteredUsers(String matcher);

    void deleteUser(int id);

}
