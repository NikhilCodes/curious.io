package com.nikhilcodes.curiousbackend.dao;

public interface AuthDao {
    void createUser(String username, String email, String passwordPlain);
}
