package com.nikhilcodes.curiousbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository("user-dao")
public class AuthDataAccess implements AuthDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createUser(String username, String email, String passwordHashed) {
        int upperBound = 999999999;
        int lowerBound = 1000;
        int user_id = lowerBound + new Random().nextInt(upperBound - lowerBound);

        jdbcTemplate.update("INSERT INTO users_db (id, username, email, password, role, enabled) VALUES (?, ?, ?, ?, ?, ?)", user_id, username, email, passwordHashed, "USER", 1);
    }
}
