package com.nhnacademy.springjpa.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("user_id"));
        user.setPassword(rs.getString("password"));
        user.setAge(rs.getInt("age"));

        return user;
    }

}
