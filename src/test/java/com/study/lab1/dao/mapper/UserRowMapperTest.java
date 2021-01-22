package com.study.lab1.dao.mapper;


import com.study.lab1.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    void testMapRow() throws SQLException {
        //when
        User user;
        //then
        user = UserRowMapper.mapRow(resultSet);
        assertEquals(1, user.getId());
        assertEquals("Nastya", user.getFirstName());
        assertEquals("Bilous", user.getLastName());
        assertEquals(0, user.getSalary());
    }

}
