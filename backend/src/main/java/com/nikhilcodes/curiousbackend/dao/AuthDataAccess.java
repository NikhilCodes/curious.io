package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.UserModel;
import com.nikhilcodes.curiousbackend.utils.RandomIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("user-dao")
public class AuthDataAccess implements AuthDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUser(String username, String email, String passwordHashed) {
        int user_id = RandomIdGenerator.generate();

        jdbcTemplate.update("INSERT INTO users_db (id, username, email, password, role, enabled) VALUES (?, ?, ?, ?, ?, ?)", user_id, username, email, passwordHashed, "USER", 1);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT id, username, email, role FROM users_db WHERE email=?", new Object[]{email}, (resultSet, i) -> new UserModel(
                resultSet.getInt("id"),
                email,
                resultSet.getString("username"),
                resultSet.getString("role")
        )));
    }
}
