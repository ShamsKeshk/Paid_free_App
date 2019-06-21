package com.example.loginapp.login.utils;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.loginapp.databinding.ActivityLoginBinding;
import com.example.loginapp.login.model.LoginUser;

import java.util.Objects;

public class ValidateUtil {


    private static final Object LOCK = new Object();

    private static ValidateUtil mValidateUtil;

    public static ValidateUtil getInstance() {
        if (mValidateUtil == null) {
            synchronized (LOCK) {
                mValidateUtil = new ValidateUtil();
            }
        }
        return mValidateUtil;
    }

    public boolean validate(LoginUser loginUser, ActivityLoginBinding binding) {
        if (null == loginUser) {
            return false;

        } else if (loginUser.getEmail() == null || TextUtils.isEmpty(Objects.requireNonNull(loginUser).getEmail().trim())) {
            binding.etEmail.setError("Enter an E-Mail Address");
            binding.etEmail.requestFocus();
            return false;
        } else if (!isEmailValid(loginUser)) {
            binding.etEmail.setError("Enter a Valid E-mail Address");
            binding.etEmail.requestFocus();
            return false;
        } else if (loginUser.getPassword() == null || TextUtils.isEmpty(Objects.requireNonNull(loginUser).getPassword())) {
            binding.etPassword.setError("Enter a Password");
            binding.etPassword.requestFocus();
            return false;
        } else if (!isPasswordLengthGreaterThanFive(loginUser)) {
            binding.etPassword.setError("Enter at least 6 Digit password");
            binding.etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmailValid(LoginUser loginUser) {
        return Patterns.EMAIL_ADDRESS.matcher(loginUser.getEmail()).matches();
    }

    private boolean isPasswordLengthGreaterThanFive(LoginUser loginUser) {
        return loginUser.getPassword().length() > 5;
    }
}
