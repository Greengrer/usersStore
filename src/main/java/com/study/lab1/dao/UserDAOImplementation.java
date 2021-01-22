package com.study.lab1.dao;

import com.study.lab1.dao.mapper.UserRowMapper;
import com.study.lab1.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation implements UserDAO {


    private static final String SELECT_ALL = "SELECT * FROM userlist ORDER BY id;";
    private static final String SELECT_ONE = "SELECT * FROM userlist WHERE id=?";
    private static final String INSERT = "INSERT INTO userlist(`first name`, `last name`, salary, `date of birth`) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE userlist SET `first name`=?, `last name`=?, salary=?, `date of birth`=? WHERE id=?";
    private static final String DELETE = "DELETE FROM userlist WHERE id=?";

    private BasicDataSource dataSource;

    public UserDAOImplementation(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<User>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)){

                ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                usersList.add(UserRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void addUser(String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, salary);
            statement.setDate(4, null);

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(int id, String firstName, String lastName, int salary, LocalDate dateOfBirth) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, salary);
            statement.setDate(4, null);
            statement.setInt(5, id);

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getOneUser(int id) {
        List<User> usersList = new ArrayList<User>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ONE)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                usersList.add(UserRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void deleteUser(int id) {

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
