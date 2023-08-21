package com.seblacko.rag.dtos;

public class AdminAuthDTO {
    private String adminPassword;
    private String email;

    public AdminAuthDTO() {
    }

    public AdminAuthDTO(String adminPassword, String email) {
        this.adminPassword = adminPassword;
        this.email = email;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
