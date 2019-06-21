package com.example.loginapp.login.model;

public class LoginUser {

    private String Email;
    private String password;

    public LoginUser() {
    }

    public LoginUser(String email, String password) {
        Email = email;
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
