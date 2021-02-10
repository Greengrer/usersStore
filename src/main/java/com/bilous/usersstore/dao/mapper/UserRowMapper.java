package com.bilous.usersstore.dao.mapper;

import com.bilous.usersstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public static User mapRow(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"),
                resultSet.getString("first name"),
                resultSet.getString("last name"),
                resultSet.getInt("salary"));
    }

}
