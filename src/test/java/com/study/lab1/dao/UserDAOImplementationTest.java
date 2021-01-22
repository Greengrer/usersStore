package com.study.lab1.dao;

import com.study.lab1.DBDataSource;
import com.study.lab1.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class UserDAOImplementationTest {

    private static final String CREATE_TABLE = "CREATE TABLE userlist " +
            "(id INT(11) not NULL AUTO_INCREMENT, " +
            "`first name` VARCHAR(65) not NULL, " +
            "`last name` VARCHAR(65) not NULL, " +
            "salary INT(11) not NULL, " +
            "`date of birth` DATE, " +
            "PRIMARY KEY ( id ))";
    private static final String INSERT_INTO_TABLE = "insert into userlist (`first name`, `last name`, salary, `date of birth`) values (?, ?, ?, ?), (?, ?, ?, ?)";
    private static final String DROP_TABLE = "DROP TABLE userlist";
    private static BasicDataSource dataSource;

    private static UserDAOImplementation userDAOImplementation;

    private static Connection connection;

    @BeforeAll
    static void beforeAll() {


        DBDataSource dbDataSource = new DBDataSource("src/test/resources/servertest.properties");

        dataSource = dbDataSource.getDataSource();

        userDAOImplementation = dbDataSource.getUserDAOImplementation();

        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @AfterAll
    static void afterAll() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @BeforeEach
    public void beforeEach() {
        try (PreparedStatement createStatement = connection.prepareStatement(CREATE_TABLE)) {
            createStatement.execute();

            try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_INTO_TABLE)) {

                insertStatement.setString(1, "Nastya");
                insertStatement.setString(2, "Bilous");
                insertStatement.setInt(3, 0);
                insertStatement.setDate(4, null);
                insertStatement.setString(5, "Oleksandr");
                insertStatement.setString(6, "Hlushkov");
                insertStatement.setInt(7, 30);
                insertStatement.setDate(8, null);
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void afterEach() {
        try (PreparedStatement statement = connection.prepareStatement(DROP_TABLE)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetAllUsersTest() {
        //when
        List<User> usersList = userDAOImplementation.getAllUsers();
        //then
        assertEquals(usersList.size(), 2);
        assertEquals(1, usersList.get(0).getId());
        assertEquals("Nastya", usersList.get(0).getFirstName());
        assertEquals("Bilous", usersList.get(0).getLastName());
        assertEquals(0, usersList.get(0).getSalary());

        assertEquals(2, usersList.get(1).getId());
        assertEquals("Oleksandr", usersList.get(1).getFirstName());
        assertEquals("Hlushkov", usersList.get(1).getLastName());
        assertEquals(30, usersList.get(1).getSalary());

    }

    @Test
    void GetOneUserTest() {
        //when
        List<User> usersList = userDAOImplementation.getOneUser(1);
        //then
        assertEquals(usersList.size(), 1);
        assertEquals(1, usersList.get(0).getId());
        assertEquals("Nastya", usersList.get(0).getFirstName());
        assertEquals("Bilous", usersList.get(0).getLastName());
        assertEquals(0, usersList.get(0).getSalary());

    }

    @Test
    void AddUserTest() {
        //when
        userDAOImplementation.addUser("Nomar", "Nagimov", 20, null);
        List<User> usersList = userDAOImplementation.getAllUsers();
        //then
        assertEquals(usersList.size(), 3);
        assertEquals(3, usersList.get(2).getId());
        assertEquals("Nomar", usersList.get(2).getFirstName());
        assertEquals("Nagimov", usersList.get(2).getLastName());
        assertEquals(20, usersList.get(2).getSalary());
    }

    @Test
    void DeleteUserTest() {
        //when
        userDAOImplementation.updateUser(2, "Nomar", "Nagimov", 20, null);
        List<User> usersList = userDAOImplementation.getAllUsers();
        //then
        assertEquals(usersList.size(), 2);
        assertEquals(2, usersList.get(1).getId());
        assertEquals("Nomar", usersList.get(1).getFirstName());
        assertEquals("Nagimov", usersList.get(1).getLastName());
        assertEquals(20, usersList.get(1).getSalary());
    }

    @Test
    void UpdateUserTest() {
        //when
        userDAOImplementation.deleteUser(2);
        List<User> usersList = userDAOImplementation.getAllUsers();
        //then
        assertEquals(usersList.size(), 1);
        assertEquals(1, usersList.get(0).getId());
        assertEquals("Nastya", usersList.get(0).getFirstName());
        assertEquals("Bilous", usersList.get(0).getLastName());
        assertEquals(0, usersList.get(0).getSalary());
    }


}
