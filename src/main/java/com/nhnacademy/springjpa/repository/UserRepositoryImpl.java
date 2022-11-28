package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.User;
import com.nhnacademy.springjpa.domain.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Objects;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean exists(String id) {
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM Users WHERE user_id = ?1", Integer.class, id);
        return count != null && count == 1;
    }

    @Override
    public boolean matches(String id, String password) {
        User user = jdbcTemplate.queryForObject("SELECT user_id, password FROM Users WHERE user_id = ?1 AND password = ?2",
                User.class, id, password);

        return Objects.nonNull(user) && user.getId().equals(id);
    }

    @Override
    public User getUser(String id) {
        return jdbcTemplate.queryForObject("SELECT user_id, password, age FROM Users where user_id = ?1", new UserRowMapper(), id);
    }

    @Override
    public boolean addUser(String id, String password, int age) {
        int result = jdbcTemplate.update("INSERT INTO Users (`user_id`, `password`, `age`) VALUES (?, ?, ?)",
                id,
                password,
                age);

        return result == 1;
    }

    @Override
    public boolean modifyUser(String id, String password) {
        int result = jdbcTemplate.update("UPDATE Users set password = ?1 WHERE user_id = ?2",
                password,
                id);

        return result == 1;
    }

}
