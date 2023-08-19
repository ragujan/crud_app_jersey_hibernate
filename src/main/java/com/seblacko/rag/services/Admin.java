package com.seblacko.rag.services;

public class Admin {
    private static final String PASSWORD = "123";
    private static final String EMAIL = "admin@gmail.com";

    public static String getPassword() {
        return Admin.PASSWORD;
    }

    public static String getEmail() {
        return Admin.EMAIL;
    }

}
