package com.seblacko.rag.util;

public class UserDetails {
    private String email;
    private String password;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDetails() {
    }

    public UserDetails(String email, String password,String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public UserDetails(String email, String type) {
        this.email = email;
        this.type = type;
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
}
