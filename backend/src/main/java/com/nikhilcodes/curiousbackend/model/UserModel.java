package com.nikhilcodes.curiousbackend.model;

public class UserModel {
    final private int id;
    final private String email;
    final private String username;
    final private String role;

    public UserModel(int id, String email, String username, String role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
