package com.bilous.usersstore.dao.defaultuser;

import com.bilous.usersstore.dao.UserDAO;
import com.bilous.usersstore.dao.mapper.UserRowMapper;
import com.bilous.usersstore.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDAO implements UserDAO {

    private static final String FIND_ALL = "SELECT id, `first name`, `last name`, salary, `date of birth` FROM userlist ORDER BY id;";
    private static final String FIND_ONE = "SELECT id, `first name`, `last name`, salary, `date of birth` FROM userlist WHERE id=?";
    private static final String FIND_FILTERED_BY_ONE_STRING = "SELECT id, `first name`, `last name`, salary, `date of birth` FROM userlist WHERE (`first name` LIKE ?) OR (`last name` LIKE ?)";
    private static final String FIND_FILTERED_BY_TWO_STRINGS = "SELECT id, `first name`, `last name`, salary, `date of birth` FROM userlist WHERE (`first name`=? AND `last name`=?) OR (`last name`=? AND`first name`=?)";
    private static final String INSERT = "INSERT INTO userlist(`first name`, `last name`, salary, `date of birth`) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE userlist SET `first name`=?, `last name`=?, salary=?, `date of birth`=? WHERE id=?";
    private static final String DELETE = "DELETE FROM userlist WHERE id=?";

    private final BasicDataSource dataSource;

    public DefaultUserDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            while (resultSet.next()) {
                usersList.add(UserRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + FIND_ALL, e);
        }
        return usersList;
    }

    @Override
    public void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        String filledStatement = INSERT;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, salary);
            statement.setDate(4, null);
            filledStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + filledStatement, e);
        }
    }

    @Override
    public void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        String filledStatement = UPDATE;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, salary);
            statement.setDate(4, null);
            statement.setInt(5, id);
            filledStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + filledStatement, e);
        }
    }

    @Override
    public List<User> findOneUser(int id) {
        List<User> usersList = new ArrayList<>();
        String filledStatement = FIND_ONE;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ONE)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            filledStatement = statement.toString();

            while (resultSet.next()) {
                usersList.add(UserRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + filledStatement + " by id=" + id, e);
        }
        return usersList;
    }

    @Override
    public void deleteUser(int id) {
        String filledStatement = DELETE;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            filledStatement = statement.toString();
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + filledStatement + " by id=" + id, e);
        }
    }

    @Override
    public List<User> findFilteredByOneStringUsers(String matcher) {

        List<User> usersList = new ArrayList<>();
        String filledStatement = FIND_FILTERED_BY_ONE_STRING;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FILTERED_BY_ONE_STRING)) {

            statement.setString(1, "%" + matcher + "%");
            statement.setString(2, "%" + matcher + "%");
            filledStatement = statement.toString();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                usersList.add(UserRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + filledStatement, e);
        }
        return usersList;
    }

    @Override
    public List<User> findFilteredByTwoStringsUsers(String firstMatcher, String secondMatcher) {

        List<User> usersList = new ArrayList<>();
        String filledStatement = FIND_FILTERED_BY_TWO_STRINGS;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FILTERED_BY_TWO_STRINGS)) {

            statement.setString(1, firstMatcher);
            statement.setString(2, secondMatcher);
            statement.setString(3, firstMatcher);
            statement.setString(4, secondMatcher);
            filledStatement = statement.toString();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                usersList.add(UserRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + filledStatement, e);
        }
        return usersList;
    }
}
