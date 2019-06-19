package com.example.loginapp.login;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;

public class LoginUser  {

    private String Email;
    private String password;

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

    public boolean isEmailValid(){
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThanFive(){
        return getPassword().length() > 5 ;
    }

}
