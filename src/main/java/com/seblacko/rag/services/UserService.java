package com.seblacko.rag.services;

import com.seblacko.rag.util.UserDetails;

public class UserService {
    private String email;
    private String password;

    public UserService() {
    }

    public UserService(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getAdminByEmailPassword(String email, String password) {
        return new UserDetails(email, password, "admin");
    }

    public UserDetails getAdminByEmail(String email, String type) {
        return new UserDetails(email, type);
    }

}
