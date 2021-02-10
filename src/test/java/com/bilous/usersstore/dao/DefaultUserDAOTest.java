package com.bilous.usersstore.dao;

import com.bilous.usersstore.dao.defaultuser.DefaultUserDAO;
import com.bilous.usersstore.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultUserDAOTest {

    private static final String CREATE_TABLE = "CREATE TABLE userlist " +
            "(id INT(11) not NULL AUTO_INCREMENT, " +
            "`first name` VARCHAR(65) not NULL, " +
            "`last name` VARCHAR(65) not NULL, " +
            "salary INT(11) not NULL, " +
            "`date of birth` DATE, " +
            "PRIMARY KEY ( id ))";
    private static final String INSERT_INTO_TABLE = "insert into userlist " +
            "(`first name`, `last name`, salary, `date of birth`) " +
            "values (?, ?, ?, ?), (?, ?, ?, ?), (?, ?, ?, ?)";
    private static final String DROP_TABLE = "DROP TABLE userlist";
    private static DefaultUserDAO defaultUserDAO;
    private static Connection connection;

    @BeforeAll
    static void beforeAll() {
        Properties properties = new Properties();
        BasicDataSource dataSource = new BasicDataSource();
        try (InputStream inputStream = new FileInputStream("src/test/resources/applicationtest.properties")) {
            properties.load(inputStream);

            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setDriverClassName(properties.getProperty("driver-class-name"));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load properties from src/test/resources/applicationtest.properties", e);
        }

        defaultUserDAO = new DefaultUserDAO(dataSource);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get connection", e);
        }
    }

    @AfterAll
    static void afterAll() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close connection", e);
        }
    }

    @BeforeEach
    public void beforeEach() {
        try (Statement createStatement = connection.createStatement()) {

            createStatement.executeUpdate(CREATE_TABLE);
            String filledStatement = "";
            try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_INTO_TABLE)) {

                insertStatement.setString(1, "Nastya");
                insertStatement.setString(2, "Bilous");
                insertStatement.setInt(3, 0);
                insertStatement.setDate(4, null);
                insertStatement.setString(5, "Oleksandr");
                insertStatement.setString(6, "Hlushkov");
                insertStatement.setInt(7, 30);
                insertStatement.setDate(8, null);
                insertStatement.setString(9, "Bilous");
                insertStatement.setString(10, "Mariya");
                insertStatement.setInt(11, 0);
                insertStatement.setDate(12, null);
                filledStatement = insertStatement.toString();
                insertStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Failed to execute " + filledStatement, e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + CREATE_TABLE, e);
        }
    }

    @AfterEach
    public void afterEachTest() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute " + DROP_TABLE, e);
        }
    }

    @Test
    void FindAllUsersTest() {
        //when
        List<User> actualUsersList = defaultUserDAO.findAllUsers();
        //then
        assertEquals(3, actualUsersList.size());
        assertEquals(1, actualUsersList.get(0).getId());
        assertEquals("Nastya", actualUsersList.get(0).getFirstName());
        assertEquals("Bilous", actualUsersList.get(0).getLastName());
        assertEquals(0, actualUsersList.get(0).getSalary());

        assertEquals(2, actualUsersList.get(1).getId());
        assertEquals("Oleksandr", actualUsersList.get(1).getFirstName());
        assertEquals("Hlushkov", actualUsersList.get(1).getLastName());
        assertEquals(30, actualUsersList.get(1).getSalary());

        assertEquals(3, actualUsersList.get(2).getId());
        assertEquals("Bilous", actualUsersList.get(2).getFirstName());
        assertEquals("Mariya", actualUsersList.get(2).getLastName());
        assertEquals(0, actualUsersList.get(2).getSalary());
    }

    @Test
    void AddUserTest() {
        //when
        defaultUserDAO.addUser("Nomar", "Nagimov", 20, null);
        List<User> actualUsersList = defaultUserDAO.findAllUsers();
        //then
        assertEquals(4, actualUsersList.size());
        assertEquals(4, actualUsersList.get(3).getId());
        assertEquals("Nomar", actualUsersList.get(3).getFirstName());
        assertEquals("Nagimov", actualUsersList.get(3).getLastName());
        assertEquals(20, actualUsersList.get(3).getSalary());
    }

    @Test
    void UpdateUserTest() {
        //when
        defaultUserDAO.updateUser(2, "Nomar", "Nagimov", 20, null);
        List<User> actualUsersList = defaultUserDAO.findAllUsers();
        //then
        assertEquals(3, actualUsersList.size());
        assertEquals(2, actualUsersList.get(1).getId());
        assertEquals("Nomar", actualUsersList.get(1).getFirstName());
        assertEquals("Nagimov", actualUsersList.get(1).getLastName());
        assertEquals(20, actualUsersList.get(1).getSalary());
    }

    @Test
    void FindOneUserTest() {
        //when
        List<User> actualUsersList = defaultUserDAO.findOneUser(1);
        //then
        assertEquals(1, actualUsersList.size());
        assertEquals(1, actualUsersList.get(0).getId());
        assertEquals("Nastya", actualUsersList.get(0).getFirstName());
        assertEquals("Bilous", actualUsersList.get(0).getLastName());
        assertEquals(0, actualUsersList.get(0).getSalary());
    }

    @Test
    void DeleteUserTest() {
        //when
        defaultUserDAO.deleteUser(2);
        List<User> actualUsersList = defaultUserDAO.findAllUsers();
        //then
        assertEquals(2, actualUsersList.size());
        assertEquals(1, actualUsersList.get(0).getId());
        assertEquals("Nastya", actualUsersList.get(0).getFirstName());
        assertEquals("Bilous", actualUsersList.get(0).getLastName());
        assertEquals(0, actualUsersList.get(0).getSalary());

        assertEquals(3, actualUsersList.get(1).getId());
        assertEquals("Bilous", actualUsersList.get(1).getFirstName());
        assertEquals("Mariya", actualUsersList.get(1).getLastName());
        assertEquals(0, actualUsersList.get(1).getSalary());
    }

    @Test
    void findFilteredByOneStringUsersTest() {
        List<User> actualUsersList = defaultUserDAO.findFilteredByOneStringUsers("us");

        assertEquals(3, actualUsersList.size());
        assertEquals(1, actualUsersList.get(0).getId());
        assertEquals("Nastya", actualUsersList.get(0).getFirstName());
        assertEquals("Bilous", actualUsersList.get(0).getLastName());
        assertEquals(0, actualUsersList.get(0).getSalary());

        assertEquals(2, actualUsersList.get(1).getId());
        assertEquals("Oleksandr", actualUsersList.get(1).getFirstName());
        assertEquals("Hlushkov", actualUsersList.get(1).getLastName());
        assertEquals(30, actualUsersList.get(1).getSalary());

        assertEquals(3, actualUsersList.get(2).getId());
        assertEquals("Bilous", actualUsersList.get(2).getFirstName());
        assertEquals("Mariya", actualUsersList.get(2).getLastName());
        assertEquals(0, actualUsersList.get(2).getSalary());
    }

    @Test
    void findFilteredByTwoStringsInStraightOrderUsersTest() {
        List<User> actualUsersList = defaultUserDAO.findFilteredByTwoStringsUsers("Nastya", "Bilous");

        assertEquals(1, actualUsersList.size());
        assertEquals(1, actualUsersList.get(0).getId());
        assertEquals("Nastya", actualUsersList.get(0).getFirstName());
        assertEquals("Bilous", actualUsersList.get(0).getLastName());
        assertEquals(0, actualUsersList.get(0).getSalary());
    }

    @Test
    void findFilteredByTwoStringsInReverseOrderUsersTest() {
        List<User> actualUsersList = defaultUserDAO.findFilteredByTwoStringsUsers("Bilous", "Nastya");

        assertEquals(1, actualUsersList.size());
        assertEquals(1, actualUsersList.get(0).getId());
        assertEquals("Nastya", actualUsersList.get(0).getFirstName());
        assertEquals("Bilous", actualUsersList.get(0).getLastName());
        assertEquals(0, actualUsersList.get(0).getSalary());
    }

}
