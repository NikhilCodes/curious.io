package com.nikhilcodes.curiousbackend.dao;

public interface AuthDao {
    public void createUser(String username, String email, String passwordPlain);
}
