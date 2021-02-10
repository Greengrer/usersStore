package com.bilous.usersstore.dao.mapper;

import com.bilous.usersstore.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    private static ResultSet resultSet;

    @BeforeAll
    static void before() throws SQLException {
        resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("first name")).thenReturn("Nastya");
        when(resultSet.getString("last name")).thenReturn("Bilous");
        when(resultSet.getInt("salary")).thenReturn(0);
    }

    @Test
    void mapRowTest() throws SQLException {
        User actualUser = UserRowMapper.mapRow(resultSet);
        assertEquals(1, actualUser.getId());
        assertEquals("Nastya", actualUser.getFirstName());
        assertEquals("Bilous", actualUser.getLastName());
        assertEquals(0, actualUser.getSalary());
    }

}
