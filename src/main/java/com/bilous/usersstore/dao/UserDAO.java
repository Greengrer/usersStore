package com.bilous.usersstore.dao;

import com.bilous.usersstore.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDAO {

    List<User> findAllUsers();

    void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth);

    void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth);

    List<User> findOneUser(int id);

    void deleteUser(int id);

    List<User> findFilteredByOneStringUsers(String matcher);

    List<User> findFilteredByTwoStringsUsers(String firstMatcher, String secondMatcher);
}
