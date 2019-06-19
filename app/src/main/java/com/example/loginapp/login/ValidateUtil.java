package com.example.loginapp.login;

import android.text.TextUtils;

import com.example.loginapp.databinding.ActivityMainBinding;

import java.util.Objects;

public class ValidateUtil {


    private static final Object LOCK = new Object();

    private static ValidateUtil mValidateUtil;

    public static ValidateUtil getInstance(){
        if (mValidateUtil == null){
            synchronized (LOCK){
                mValidateUtil = new ValidateUtil();
            }
        }
        return mValidateUtil;
    }



    public boolean validate(LoginUser loginUser, ActivityMainBinding binding){
        if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getEmail().trim())) {
            binding.etEmail.setError("Enter an E-Mail Address");
            binding.etEmail.requestFocus();
            return false;
        }
        else if (!loginUser.isEmailValid()) {
            binding.etEmail.setError("Enter a Valid E-mail Address");
            binding.etEmail.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getPassword())) {
            binding.etPassword.setError("Enter a Password");
            binding.etPassword.requestFocus();
            return false;
        }
        else if (!loginUser.isPasswordLengthGreaterThanFive()) {
            binding.etPassword.setError("Enter at least 6 Digit password");
            binding.etPassword.requestFocus();
            return false;
        }else {
            return true;
        }
    }
}
