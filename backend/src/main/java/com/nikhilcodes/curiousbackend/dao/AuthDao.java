package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.UserModel;

public interface AuthDao {
    void createUser(String username, String email, String passwordPlain);
}
