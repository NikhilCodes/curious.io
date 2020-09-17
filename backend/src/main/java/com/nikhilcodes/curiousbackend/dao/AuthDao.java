package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.UserModel;

import java.util.Optional;

public interface AuthDao {
    void createUser(String username, String email, String passwordPlain);

    Optional<UserModel> getUserByEmail(String email);
}
